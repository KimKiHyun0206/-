package com.example.digitalsample;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class CookieController {

    //http://localhost:8080/cookie?id=10&password=23
    @GetMapping("/cookie")
    public String cookie(HttpServletResponse response, @RequestParam String id, @RequestParam String password, Model model) {
        Cookie cookie = new Cookie(id, password);
        cookie.setMaxAge(100);

        model.addAttribute("id", id);
        model.addAttribute("password", password);

        response.addCookie(cookie);
        log.info("Cookie 를 생성했습니다 {}", id);

        return "cookie";
    }

    //http://localhost:8080/use-cookie
    @GetMapping("/use-cookie")
    public String useCookie(@CookieValue(name = "10", required = false) String id, Model model) {
        log.info("Cookie 를 받아왔습니다 {}", id);
        model.addAttribute("id", id);

        return "use_cookie";
    }

}
