package com.luizalabs.legacyorderapi.responses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductResponseTest {

    private final int PRODUCT_ID = 1;
    private final String PRODUCT_VALUE = "256.24";

    @Test
    public void testGettersSettersAndNoArgsContructor() {
        ProductResponse response = new ProductResponse();
        response.setProduct_id(PRODUCT_ID);
        response.setValue(PRODUCT_VALUE);

        assertEquals(PRODUCT_ID, response.getProduct_id());
        assertEquals(PRODUCT_VALUE, response.getValue());
    }

    @Test
    public void testAllArgsConstructor() {
        ProductResponse response = new ProductResponse(PRODUCT_ID, PRODUCT_VALUE);

        assertEquals(PRODUCT_ID, response.getProduct_id());
        assertEquals(PRODUCT_VALUE, response.getValue());
    }

}

