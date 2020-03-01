package com.erebus.registration.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/success")
public class SuccessController {

    @GetMapping
    private String success() throws InterruptedException {
        return "success";
    }
}
