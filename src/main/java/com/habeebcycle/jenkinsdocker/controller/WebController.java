package com.habeebcycle.jenkinsdocker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebController {

    @GetMapping("")
    public String greet(){
        return "Welcome to our API";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Thank you and welcome to hour house";
    }

    @GetMapping("welcome/{msg}")
    public String message(@PathVariable String msg){
        return "You write: "+msg;
    }
}
