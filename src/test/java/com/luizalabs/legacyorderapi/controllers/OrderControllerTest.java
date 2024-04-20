package com.luizalabs.legacyorderapi.controllers;

import com.luizalabs.legacyorderapi.entities.Order;
import com.luizalabs.legacyorderapi.responses.UploadResponse;
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
    private OrderService service;

    @InjectMocks
    private OrderController controller;

    @SuppressWarnings("null")
    @Test
    void testUploadFileSuccess() throws IOException {
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
    void testUploadFileFailure() throws IOException {
        MockMultipartFile mockFile = getMockMultipartFile();

        when(service.processFile(mockFile)).thenThrow(new IOException());
        ResponseEntity<UploadResponse> responseEntity = controller.uploadFile(mockFile);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(UPLOAD_MESSAGE_FAILURE, responseEntity.getBody().getMessage());
    }

    private static MockMultipartFile getMockMultipartFile() {
        MockMultipartFile mockedFile  = new MockMultipartFile("file", "test.txt", "text/plain", "Mock file content".getBytes());
        return mockedFile;
    }
}
