package com.example.digitalsample;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CookieService {

    public Cookie makeCookie(String id){
        Cookie cookie = new Cookie(
                "DigitalLoginCookie"
                , Base64.getEncoder().encodeToString(id.getBytes())
        );

        cookie.setMaxAge(10);

        return cookie;
    }
}
