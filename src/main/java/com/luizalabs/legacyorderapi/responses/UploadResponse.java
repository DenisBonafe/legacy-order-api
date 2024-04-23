package com.luizalabs.legacyorderapi.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadResponse {
    private String message;
    private int size;
}
