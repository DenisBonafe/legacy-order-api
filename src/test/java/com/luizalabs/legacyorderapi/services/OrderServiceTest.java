package com.luizalabs.legacyorderapi.services;

import com.luizalabs.legacyorderapi.entities.Order;
import com.luizalabs.legacyorderapi.repositories.OrderRepository;
import com.luizalabs.legacyorderapi.responses.UsersResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.luizalabs.legacyorderapi.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    @Test
    void testProcessFile() throws IOException {
        String content = "0000000022                             Rosendo Hartmann00000002380000000004     1180.3920210708\n";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        MultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", inputStream);

        List<Order> orders = service.processFile(multipartFile);
        Order order = orders.get(0);
        order.setId(UUID.randomUUID());

        assertEquals(1, orders.size());
        assertEquals(22, order.getUserId());
        assertEquals("Rosendo Hartmann", order.getUserName());
        assertEquals(238, order.getOrderId());
        assertEquals(4, order.getProductId());
        assertEquals(1180.39, order.getProductValue());
        assertEquals(LocalDate.parse("2021-07-08"), order.getOrderDate());
    }

    @Test
    public void testSaveOrders() {
        UUID uuid = UUID.randomUUID();
        Order orderA = new Order(uuid, 1, "Fulano de Tal", 111, 222,
                123.45, LocalDate.of(2021, 7, 8));
        Order orderB = new Order(uuid, 2, "Zezin das Tantas", 112, 223,
                124.35, LocalDate.of(2021, 7, 9));
        List<Order> orders = new ArrayList<>();
        orders.add(orderA);
        orders.add(orderB);

        when(service.saveOrders(orders)).thenReturn(orders);
        List<Order> savedOrders = service.saveOrders(orders);
        when(repository.findAll()).thenReturn(orders);
        List<Order> retrievedOrders = repository.findAll();

        assertEquals(orders, savedOrders);
        assertEquals(orders, retrievedOrders);
    }

    @Test
    void testGetOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(UUID_A, USER_ID_A, USER_NAME_A, ORDER_ID_A, PRODUCT_ID_A, PRODUCT_VALUE_A, INITIAL_DATE_A));

        when(repository.findAll()).thenReturn(orders);
        List<Order> result = service.getAllOrders();
        assertEquals(orders, result);
    }

    @Test
    void testGetUsersResponse() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(UUID_A, USER_ID_A, USER_NAME_A, ORDER_ID_A, PRODUCT_ID_A, PRODUCT_VALUE_A, INITIAL_DATE_A));
        orders.add(new Order(UUID_B, USER_ID_B, USER_NAME_B, ORDER_ID_B, PRODUCT_ID_B, PRODUCT_VALUE_B, INITIAL_DATE_B));
        UsersResponse usersResponse = service.getUsersResponse(orders);

        assertEquals(2, usersResponse.getCount());
    }
}
