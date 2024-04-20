package com.luizalabs.legacyorderapi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int order_id;
    private String total;
    private String date;
    private List<ProductResponse> products;
}
