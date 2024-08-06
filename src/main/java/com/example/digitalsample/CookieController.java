package com.example.digitalsample;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CookieController {

    private final CookieService cookieService;

    @RequestMapping("/login")
    public String login(@RequestParam(name = "id") String id, HttpServletResponse response, HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();

        Cookie loginCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("DigitalLoginCookie")) {
                loginCookie = cookie;
                model.addAttribute("action", "쿠키가 있으므로 쿠키로 로그인했습니다");
                model.addAttribute("cookieValue", cookie.getValue());
                break;
            }
        }

        if (loginCookie == null) {
            Cookie cookie = cookieService.makeCookie(id);
            response.addCookie(cookie);
            model.addAttribute("action", "쿠키가 없으므로 로그인 한 후 쿠키를 발급했습니다");
            model.addAttribute("cookieValue", cookie.getValue());
        }

        return "cookie";
    }
}
