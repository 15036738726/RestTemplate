package com.example.controller;

import com.example.domain.User;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * get请求测试
 */
@RestController
public class GetTypeController {

    /**
     * 不带参请求
     * @return
     */
    @GetMapping("getTest1")
    public User getTest1() {
        return User.builder().id(1).name("xixi").price(1000D).build();
    }

    /**
     * 带参的get请求
     * @param id
     * @param name
     * @return
     */
    @GetMapping("getTest2/{id}/{name}")
    public User getTest2(@PathVariable("id") Integer id, @PathVariable("name")String name){
        return User.builder().id(id).name(name).build();
    }

    /**
     * 带参的get请求
     * @param id
     * @param name
     * @return
     */
    @GetMapping("getTest3")
    public User getTest3(@RequestParam("id") Integer id, @RequestParam("name")String name){
        return User.builder().id(id).name(name).build();
    }

}
