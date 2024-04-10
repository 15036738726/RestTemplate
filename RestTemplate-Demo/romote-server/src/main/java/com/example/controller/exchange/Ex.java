package com.example.controller.exchange;

import com.example.domain.Result;
import com.example.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Ex {


    @GetMapping("get")
    public String get(@RequestParam("name") String name){
        return "test"+name;
    }

    @GetMapping("getUser")
    public User getUser(@RequestParam("name") String name){
        User build = User.builder().id(1)
                .name(name).build();
        return build;
    }

    @GetMapping("getTypeRtnList")
    public List<User>  getTypeRtnList(@RequestParam("name") String name){
        List<User> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            list.add(User.builder().id(i)
                    .name(name + i).build());
        }
        return list;
    }

    @GetMapping("getMap")
    public Map<String,Object> getMap(@RequestParam("key") String key, @RequestParam("val")Integer val){
        Map<String, Object> map = new HashMap<>(2);
        map.put(key, val);
        return map;
    }

    @GetMapping("getCustomType")
    public Result<User> getCustomType(@RequestParam("name") String name, @RequestParam("id") Integer id) {
        User build = User.builder().id(id).name(name).build();
        Result<User> result = Result.success(build);
        return result;
    }

    @GetMapping("getResultList")
    public List<Result<User>>  getResultList(@RequestParam("name") String name, @RequestParam("id") Integer id) {
        List<Result<User>> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            User user = User.builder()
                    .id(i)
                    .name(name + i)
                    .build();
            list.add(Result.success(user));
        }
        return list;
    }

    ///////////////////下面是POST接口////////////////////
    @PostMapping("testPostUser")
    public User testPostUser(@RequestBody User user){
        return user;
    }

    @PostMapping("testPostResult")
    public Result<User> testPostResult(@RequestBody User user){
        return Result.success(user);
    }



}
