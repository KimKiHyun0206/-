package com.example.digitalsample.repository;

import com.example.digitalsample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
