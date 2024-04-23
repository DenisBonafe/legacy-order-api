package com.luizalabs.legacyorderapi.responses;

import org.junit.jupiter.api.Test;

import static com.luizalabs.legacyorderapi.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UploadResponseTest {

    @Test
    public void testAllArgsConstructor() {
        UploadResponse response = new UploadResponse(UPLOAD_MESSAGE_SUCCESS, 10);;
        assertEquals(UPLOAD_MESSAGE_SUCCESS, response.getMessage());
        assertEquals(10, response.getSize());
    }

    @Test
    public void testGettersAndSetters() {
        UploadResponse response = new UploadResponse(UPLOAD_MESSAGE_SUCCESS, 10);;
        assertEquals(UPLOAD_MESSAGE_SUCCESS, response.getMessage());
        assertEquals(10, response.getSize());

        response.setMessage(UPLOAD_MESSAGE_FAILURE);
        response.setSize(0);

        assertEquals(UPLOAD_MESSAGE_FAILURE, response.getMessage());
        assertEquals(0, response.getSize());
    }

}
