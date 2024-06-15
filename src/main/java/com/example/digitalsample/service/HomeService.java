package com.example.digitalsample.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {
    public String hello(String name) {
        return "Hello! " + name;
    }

    public int leftTimeCal(int time) {
        return 24 - time;
    }

    public Long calculateFactorial(Long num) {
        long answer = 1L;

        if (num < 2) return 1L;

        for (int i = 2; i <= num; i++) {
            answer *= i;
        }

        return answer;
    }
}
