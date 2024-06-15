package com.example.digitalsample.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRegisterRequest {
    private Long id;
    private String name;
    private Long age;
}
