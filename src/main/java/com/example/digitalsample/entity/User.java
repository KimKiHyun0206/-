package com.example.digitalsample.entity;

import com.example.digitalsample.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20,nullable = false)
    private String name;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_passward")
    private String password;

    public User(String name, String userId, String password){
        this.name = name;
        this.userId = userId;
        this.password = password;

    }

    public void update(String name,String userId, String password){
        this.name = name;
        this.userId = userId;
        this.password = password;
    }
}
