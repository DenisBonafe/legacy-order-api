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
public class UsersResponse {
    List<UserResponse> ordersByUser;
    int count;
}