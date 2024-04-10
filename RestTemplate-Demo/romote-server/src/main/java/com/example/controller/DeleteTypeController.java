package com.example.controller;

import com.example.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteTypeController {

    @DeleteMapping("delTest1")
    public void delTest1(){
        System.out.println("-------");
    }

    @DeleteMapping("delTest2/{id}")
    public void delTest2(@PathVariable("id") Integer id){
        System.out.println(id);
    }

    @DeleteMapping("delTest3")
    public User delTest3(@RequestBody User user){
        return user;
    }
}
