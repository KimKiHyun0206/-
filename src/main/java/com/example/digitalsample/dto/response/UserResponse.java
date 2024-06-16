package com.example.digitalsample.dto.response;


import com.example.digitalsample.entity.User;
import lombok.Data;

@Data
public class UserResponse {
    private final Long id;
    private final String name;
    private final String userId;
    private final String password;

    public static UserResponse from(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUserId(),
                user.getPassword()
        );
    }
}
