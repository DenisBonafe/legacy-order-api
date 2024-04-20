package com.luizalabs.legacyorderapi.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int user_id;
    private String name;
    private List<OrderResponse> orders;
}
