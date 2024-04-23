package com.luizalabs.legacyorderapi.controllers;

import com.luizalabs.legacyorderapi.entities.Order;
import com.luizalabs.legacyorderapi.repositories.OrderRepository;
import com.luizalabs.legacyorderapi.responses.OrderResponse;
import com.luizalabs.legacyorderapi.responses.ProductResponse;
import com.luizalabs.legacyorderapi.responses.UploadResponse;
import com.luizalabs.legacyorderapi.responses.UserResponse;
import com.luizalabs.legacyorderapi.responses.UsersResponse;
import com.luizalabs.legacyorderapi.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.luizalabs.legacyorderapi.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private OrderService service;

    @InjectMocks
    private OrderController controller;

    @SuppressWarnings("null")
    @Test
    public void testUploadFileSuccess() throws IOException {
        MockMultipartFile mockedFile = getMockMultipartFile();
        List<Order> mockedOrders = new ArrayList<>();

        when(service.processFile(mockedFile)).thenReturn(mockedOrders);
        ResponseEntity<UploadResponse> responseEntity = controller.uploadFile(mockedFile);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(UPLOAD_MESSAGE_SUCCESS, responseEntity.getBody().getMessage());
        verify(service, times(1)).saveOrders(mockedOrders);
    }

    @SuppressWarnings("null")
    @Test
    public void testUploadFileFailure() throws IOException {
        MockMultipartFile mockFile = getMockMultipartFile();

        when(service.processFile(mockFile)).thenThrow(new IOException());
        ResponseEntity<UploadResponse> responseEntity = controller.uploadFile(mockFile);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(UPLOAD_MESSAGE_FAILURE, responseEntity.getBody().getMessage());
    }

    @Test
    public void testListOrdersError() {
        List<Order> mockOrderList = mockOrderList();
        
        when(repository.findAll()).thenReturn(mockOrderList);
        UsersResponse expectedUsersResponse = service.getUsersResponse(mockOrderList);

        when(service.getUsersResponse(mockOrderList)).thenReturn(expectedUsersResponse);
        ResponseEntity<UsersResponse> actualResponseEntity = controller.listOrders();
        int count = 0;

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponseEntity.getStatusCode());
        assertEquals(0, count);
    }

    @SuppressWarnings("null")
    @Test
    public void testListOrdersSuccessful() {
        List<ProductResponse> productResponseList = new ArrayList<>();
        List<OrderResponse> orderResponseList = new ArrayList<>();
        List<UserResponse> userResponseList = new ArrayList<>();
        List<Order> mockOrderList = mockOrderList();

        ProductResponse productResponse = new ProductResponse(PRODUCT_ID_A, String.valueOf(PRODUCT_VALUE_A));
        productResponseList.add(productResponse);

        OrderResponse orderResponse = new OrderResponse(ORDER_ID_A, productResponse.getValue(), ORDER_DATE_A.toString(),
                productResponseList);
        orderResponseList.add(orderResponse);

        UserResponse userResponse = new UserResponse(USER_ID_A, USER_NAME_A, orderResponseList);
        userResponseList.add(userResponse);

        UsersResponse usersResponse = new UsersResponse(userResponseList, 1);

        when(service.getOrders()).thenReturn(mockOrderList);
        when(service.getUsersResponse(mockOrderList)).thenReturn(usersResponse);
        ResponseEntity<UsersResponse> actualResponseEntity = controller.listOrders();

        assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());
        assertEquals(1, actualResponseEntity.getBody().getCount());
    }

    private static List<Order> mockOrderList() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(UUID_A, USER_ID_A, USER_NAME_A, ORDER_ID_A, PRODUCT_ID_A, PRODUCT_VALUE_A, ORDER_DATE_A));
        orders.add(new Order(UUID_B, USER_ID_B, USER_NAME_B, ORDER_ID_B, PRODUCT_ID_B, PRODUCT_VALUE_B, ORDER_DATE_B));
        return orders;
    }

    private static MockMultipartFile getMockMultipartFile() {
        MockMultipartFile mockedFile  = new MockMultipartFile("file", "test.txt", "text/plain", "Mock file content".getBytes());
        return mockedFile;
    }
}
