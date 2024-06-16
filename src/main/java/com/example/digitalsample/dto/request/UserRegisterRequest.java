package com.example.digitalsample.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private final String name;
    private final String userId;
    private final String password;
}
