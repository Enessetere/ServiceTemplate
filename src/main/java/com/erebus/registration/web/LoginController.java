package com.erebus.registration.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lin")
public class LoginController {

    @GetMapping
    public String login() {
        return "lin";
    }
}
