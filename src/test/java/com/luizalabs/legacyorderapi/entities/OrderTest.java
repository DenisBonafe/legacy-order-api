package com.luizalabs.legacyorderapi.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static com.luizalabs.legacyorderapi.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    private UUID orderId = UUID_A;
    private int userId = USER_ID_A;
    private String userName = USER_NAME_A;
    private int orderIdValue = ORDER_ID_A;
    private int productId = PRODUCT_ID_A;
    private double productValue = PRODUCT_VALUE_A;
    private LocalDate orderDate = INITIAL_DATE_A;

    @Test
    public void testCreateOrderAllArgsConstructor() {
        Order order = new Order(orderId, userId, userName, orderIdValue, productId, productValue, orderDate);

        assertEquals(orderId, order.getId());
        assertEquals(userId, order.getUserId());
        assertEquals(userName, order.getUserName());
        assertEquals(orderIdValue, order.getOrderId());
        assertEquals(productId, order.getProductId());
        assertEquals(productValue, order.getProductValue());
        assertEquals(orderDate, order.getOrderDate());
    }

    @Test
    public void testCreateOrderNoArgsConstructor() {
        Order order = new Order();
        order.setId(orderId);
        order.setUserId(userId);
        order.setUserName(userName);
        order.setOrderId(orderIdValue);
        order.setProductId(productId);
        order.setProductValue(productValue);
        order.setOrderDate(orderDate);

        assertEquals(orderId, order.getId());
        assertEquals(userId, order.getUserId());
        assertEquals(userName, order.getUserName());
        assertEquals(orderIdValue, order.getOrderId());
        assertEquals(productId, order.getProductId());
        assertEquals(productValue, order.getProductValue());
        assertEquals(orderDate, order.getOrderDate());
    }

}
