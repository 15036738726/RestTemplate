package com.example.controller;


import com.example.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *    localhost:8080/test1
 */
@RestController
public class GetTestController {

    @Resource
    private RestTemplate restTemplate;

    /**
     *     不带参请求
     *     @GetMapping("getTest1")
     *     public User getTest1()
     * @return
     */
    @GetMapping("test1")
    public User test1() {
        String url = "http://localhost:8081/getTest1";
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    /**
     * 带参的get请求
     *     @GetMapping("getTest2/{id}/{name}")
     *     public User getTest2(@PathVariable("id") Integer id, @PathVariable("name")String name){
     * @return
     */
    @GetMapping("test2")
    public User test2() {
        String url = "http://localhost:8081/getTest2/2/zhangsan";
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    /**
     * 带参的get请求
     *     @GetMapping("getTest3")
     *     public User getTest3(@RequestParam("id") Integer id, @RequestParam("name")String name){
     * @return
     */
    @GetMapping("test3")
    public User test3() {
        String url = "http://localhost:8081/getTest3?id=3&name=lisi";
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }

    /**
     * 传map
     * @return
     */
    @GetMapping("test4")
    public User test4() {
        String url = "http://localhost:8081/getTest3?id={id}&name={name}";
        Map<String, Object> map = new HashMap<>();
        map.put("id",4);
        map.put("name", "zhaoliu");
        User user = restTemplate.getForObject(url, User.class,map);
        return user;
    }
}
