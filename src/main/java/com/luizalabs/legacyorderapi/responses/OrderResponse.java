package com.luizalabs.legacyorderapi.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private int order_id;
    private String total;
    private String date;
    private List<ProductResponse> products;
}
