package com.luizalabs.legacyorderapi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResponse {
    private String message;
    private int size;
}
