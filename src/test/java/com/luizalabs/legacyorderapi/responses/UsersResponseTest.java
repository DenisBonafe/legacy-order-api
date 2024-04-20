package com.luizalabs.legacyorderapi.responses;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersResponseTest {

    private final int ARRAY_SIZE = 0;
    private final List<UserResponse> USERS_RESPONSE = new ArrayList<>();

    @Test
    public void testGettersSettersAndNoArgsContructor() {
        UsersResponse response = new UsersResponse();

        response.setOrdersByUser(USERS_RESPONSE);
        response.setCount(ARRAY_SIZE);

        assertEquals(USERS_RESPONSE, response.getOrdersByUser());
        assertEquals(ARRAY_SIZE, response.getOrdersByUser().size());
    }

    @Test
    public void testAllArgsConstructor() {
        UsersResponse response = new UsersResponse(USERS_RESPONSE, ARRAY_SIZE);

        assertEquals(USERS_RESPONSE, response.getOrdersByUser());
        assertEquals(ARRAY_SIZE, response.getCount());
    }

}
