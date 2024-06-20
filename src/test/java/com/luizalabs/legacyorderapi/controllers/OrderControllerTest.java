package com.luizalabs.legacyorderapi.controllers;

import com.luizalabs.legacyorderapi.entities.Order;
import com.luizalabs.legacyorderapi.repositories.OrderRepository;
import com.luizalabs.legacyorderapi.responses.UploadResponse;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.luizalabs.legacyorderapi.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void testListOrdersAllParametersNull() {
        List<Order> orders = Collections.singletonList(new Order());

        when(service.getAllOrders()).thenReturn(orders);
        ResponseEntity<UsersResponse> response = controller.listOrders(null, null, null);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // @Test
    // void testListOrdersStartDateAndEndDateProvided() {
    //     List<Order> orders = mockOrderList();
    //     LocalDate startDate = INITIAL_DATE_A;
    //     LocalDate endDate = LocalDate.now();

    //     when(controller.getFilteredOrders(null, startDate.toString(), endDate.toString())).thenReturn(orders);
    //     ResponseEntity<UsersResponse> response = controller.listOrders(null, startDate.toString(), endDate.toString());

    //     assertNotNull(response);
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    // }

    @Test
    void testListOrdersOrderIdProvided() {
        List<Order> orders = mockOrderList();
        int orderId = ORDER_ID_A;

        when(service.getOrdersFilteredByOrderId(orderId)).thenReturn(orders);
        ResponseEntity<UsersResponse> response = controller.listOrders(orderId, null, null);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // @Test
    // void testListOrdersOrderIdAndStartDateAndEndDateProvided() {
    //     List<Order> orders = mockOrderList();
    //     int orderId = ORDER_ID_A;
    //     LocalDate startDate = ORDER_DATE_A;
    //     LocalDate endDate = LocalDate.now();
        
    //     when(controller.getFilteredOrders(orderId, startDate.toString(), endDate.toString())).thenReturn(orders);
    //     ResponseEntity<UsersResponse> response = controller.listOrders(orderId, startDate.toString(), endDate.toString());

    //     assertNotNull(response);
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    // }

    @Test
    void testListOrdersWhenFilteredOrdersIsNull() {
        ResponseEntity<UsersResponse> response = controller.listOrders(null, null, LocalDate.now().toString());

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testExtractedCatchBlock() {
        List<Order> orders = mockOrderList();
        
        when(service.getUsersResponse(orders)).thenThrow(new RuntimeException("Mock Exception"));
        ResponseEntity<UsersResponse> response = controller.extracted(orders);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    private static List<Order> mockOrderList() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(UUID_A, USER_ID_A, USER_NAME_A, ORDER_ID_A, PRODUCT_ID_A, PRODUCT_VALUE_A, INITIAL_DATE_A));
        return orders;
    }

    private static MockMultipartFile getMockMultipartFile() {
        MockMultipartFile mockedFile  = new MockMultipartFile("file", "test.txt", "text/plain", "Mock file content".getBytes());
        return mockedFile;
    }
}
