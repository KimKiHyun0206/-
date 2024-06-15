package com.example.digitalsample.controller;

import com.example.digitalsample.service.HomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name){
        return homeService.hello(name);
    }

    @GetMapping("/calculate/{time}")
    public String leftTime(@PathVariable int time, Model model){
        model.addAttribute("time", time);
        model.addAttribute("lefTime",homeService.leftTimeCal(time));

        return "left_time";
    }

    @GetMapping("/factorial")
    public String factorial(@RequestParam Long number, Model model){
        model.addAttribute("answer", homeService.calculateFactorial(number));
        model.addAttribute("number",number);
        return "factorial";
    }
}
