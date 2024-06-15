package com.example.digitalsample.controller;

import com.example.digitalsample.dto.UserRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
public class UserController {

    @GetMapping("/user/reister")
    public String register(@ModelAttribute UserRegisterRequest request){

    }
}
