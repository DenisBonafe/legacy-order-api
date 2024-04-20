package com.luizalabs.legacyorderapi.responses;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseTest {

    private final int USER_ID = 1;
    private final String USER_NAME = "256.24";
    private final List<OrderResponse> ORDERS_RESPONSE = new ArrayList<>();

    @Test
    public void testGettersSettersAndNoArgsContructor() {
        UserResponse response = new UserResponse();

        response.setUser_id(USER_ID);
        response.setName(USER_NAME);
        response.setOrders(ORDERS_RESPONSE);

        assertEquals(USER_ID, response.getUser_id());
        assertEquals(USER_NAME, response.getName());
        assertEquals(ORDERS_RESPONSE, response.getOrders());
    }

    @Test
    public void testAllArgsConstructor() {
        UserResponse response = new UserResponse(USER_ID, USER_NAME, ORDERS_RESPONSE);

        assertEquals(USER_ID, response.getUser_id());
        assertEquals(USER_NAME, response.getName());
        assertEquals(ORDERS_RESPONSE, response.getOrders());
    }

}
