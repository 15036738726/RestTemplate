package com.example.controller;

import com.example.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PostTypeController {

    /**
     * 模拟表单参数
     * @return
     */
    @PostMapping("postTest1")
    public User postTest1(@RequestParam("id") Integer id,@RequestParam("name") String name) {
        User user = User.builder().id(id).name(name).build();
        return user;
    }

    /**
     * 表单传递对象参数
     * @param user
     * @return
     */
    @PostMapping("postTest2")
    public User postTest2(User user) {
        return user;
    }

    /**
     * 发送方传递对象,接收方使用@RequestBody+对象接收
     * @return
     */
    @PostMapping("postTest3")
    public User postTest3(@RequestBody User user) {
        return user;
    }

    /**
     * 发送方传递map参数 接收方使用@RequestBody+对象接收
     * @param user
     * @return
     */
    @PostMapping("postTest4")
    public User postTest4(@RequestBody User user) {
        return user;
    }


    /**
     * 重定向
     * @param user
     * @return
     */
    @PostMapping("testPostByLocation")
    public String testPostByLocation(@RequestBody User user){
        System.out.println(user);
        return "redirect:index.html";
    }




}
