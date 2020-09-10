package com.github.viktorgozhiy.authenticator.controller;

import com.github.viktorgozhiy.authenticator.support.API;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO Will be removed!

@RestController
public class TestController {

    @GetMapping(API.API + "hello/")
    public String hello() {
        return "Hello world!";
    }
}
