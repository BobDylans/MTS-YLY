package com.mtsyl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWeXin {
    @RequestMapping("/getMessage")
    public String getMessage(Integer userId){
        return "Hello 微信用户"+userId+"!";

    }
}
