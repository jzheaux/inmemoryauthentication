package com.example.filterchain3.controlller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    @GetMapping("/admin")
    public String admindata()
    {
        return "admin page";
    }
    @GetMapping("/user")
    public String userpage()
    {
        return "user page";
    }
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }
}
