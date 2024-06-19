package com.example.digitalsample.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller("/session")
public class SessionController {

    @RequestMapping("/create")
    public String createSession(HttpServletRequest request, Model model, @RequestParam String value, @RequestParam String key) {

        log.info("Create Session {} {}", key, value);

        HttpSession session = request.getSession();
        session.setAttribute(key, value);

        model.addAttribute("key", key);
        model.addAttribute("value", value);

        return "session";
    }

    @RequestMapping("/use")
    public String useSession(HttpServletRequest request, @RequestParam String id, Model model) {
        log.info("Use Session {}", id);
        HttpSession session = request.getSession();
        String idFromSession = (String) session.getAttribute(id);

        model.addAttribute("key", id);
        model.addAttribute("value", idFromSession);

        return "use_session";
    }

}
