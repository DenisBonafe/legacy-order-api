package com.luizalabs.legacyorderapi.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static com.luizalabs.legacyorderapi.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderTest {

    private UUID orderIdA = UUID_A;
    private int userIdA = USER_ID_A;
    private String userNameA = USER_NAME_A;
    private int orderIdValueA = ORDER_ID_A;
    private int productIdA = PRODUCT_ID_A;
    private double productValueA = PRODUCT_VALUE_A;
    private LocalDate orderDateA = ORDER_DATE_A;
    private UUID orderIdB = UUID_B;
    private int userIdB = USER_ID_B;
    private String userNameB = USER_NAME_B;
    private int orderIdValueB = ORDER_ID_B;
    private int productIdB = PRODUCT_ID_B;
    private double productValueB = PRODUCT_VALUE_B;
    private LocalDate orderDateB = ORDER_DATE_B;

    @Test
    public void testCreateOrderAllArgsConstructor() {
        Order order = new Order(orderIdA, userIdA, userNameA, orderIdValueA, productIdA, productValueA, orderDateA);

        assertEquals(orderIdA, order.getId());
        assertEquals(userIdA, order.getUserId());
        assertEquals(userNameA, order.getUserName());
        assertEquals(orderIdValueA, order.getOrderId());
        assertEquals(productIdA, order.getProductId());
        assertEquals(productValueA, order.getProductValue());
        assertEquals(orderDateA, order.getOrderDate());
    }

    @Test
    public void testCreateOrderNoArgsConstructor() {
        Order order = new Order();
        order.setId(orderIdA);
        order.setUserId(userIdA);
        order.setUserName(userNameA);
        order.setOrderId(orderIdValueA);
        order.setProductId(productIdA);
        order.setProductValue(productValueA);
        order.setOrderDate(orderDateA);

        assertEquals(orderIdA, order.getId());
        assertEquals(userIdA, order.getUserId());
        assertEquals(userNameA, order.getUserName());
        assertEquals(orderIdValueA, order.getOrderId());
        assertEquals(productIdA, order.getProductId());
        assertEquals(productValueA, order.getProductValue());
        assertEquals(orderDateA, order.getOrderDate());
    }

    @Test
    public void testEqualsAndHashCode() {
        Order orderA = new Order(orderIdA, userIdA, userNameA, orderIdValueA, productIdA, productValueA, orderDateA);
        Order orderB = new Order(orderIdA, userIdA, userNameA, orderIdValueA, productIdA, productValueA, orderDateA);

        assertEquals(orderA, orderB);
        assertEquals(orderA.hashCode(), orderB.hashCode());

        orderB.setId(orderIdB);
        orderB.setUserId(userIdB);
        orderB.setUserName(userNameB);
        orderB.setOrderId(orderIdValueB);
        orderB.setProductId(productIdB);
        orderB.setProductValue(productValueB);
        orderB.setOrderDate(orderDateB);

        assertNotEquals(orderA, orderB);
        assertNotEquals(orderA.hashCode(), orderB.hashCode());
    }

}
