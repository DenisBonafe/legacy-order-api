package com.luizalabs.legacyorderapi.responses;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderResponseTest {

    private final int ORDER_ID = 1;
    private final String ORDER_TOTAL = "12345.67";
    private final String ORDER_DATE = "2021-07-08";
    private final List<ProductResponse> PRODUCT_RESPONSES = new ArrayList<>();

    @Test
    public void testGettersSettersAndNoArgsContructor() {
        OrderResponse response = new OrderResponse();
        response.setOrder_id(ORDER_ID);
        response.setTotal(ORDER_TOTAL);
        response.setDate(ORDER_DATE);
        response.setProducts(PRODUCT_RESPONSES);

        assertEquals(ORDER_ID, response.getOrder_id());
        assertEquals(ORDER_TOTAL, response.getTotal());
        assertEquals(ORDER_DATE, response.getDate());
        assertEquals(PRODUCT_RESPONSES, response.getProducts());
    }

    @Test
    public void testAllArgsConstructor() {
        OrderResponse response = new OrderResponse(ORDER_ID, ORDER_TOTAL, ORDER_DATE, PRODUCT_RESPONSES);

        assertEquals(ORDER_ID, response.getOrder_id());
        assertEquals(ORDER_TOTAL, response.getTotal());
        assertEquals(ORDER_DATE, response.getDate());
        assertEquals(PRODUCT_RESPONSES, response.getProducts());
    }

}
