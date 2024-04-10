package com.example.controller;

import com.example.domain.User;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PutTypeController {

    @PutMapping("putTest1")
    public void  putTest1(@RequestBody User user){
        System.out.println(user);
    }



    @PutMapping("putTest2")
    public User  putTest2(@RequestBody User user){
        return user;
    }
}
