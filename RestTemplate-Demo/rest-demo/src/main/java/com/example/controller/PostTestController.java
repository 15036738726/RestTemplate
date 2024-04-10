package com.example.controller;

import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * localhost:8080/postTest1
 */
@RestController
public class PostTestController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     *  表单参数
     *     @PostMapping("postTest1")
     *     public User postTest1(@RequestParam("id") Integer id,@RequestParam("name") String name) {
     *
     * @return
     */
    @GetMapping("postTest1")
    public User postTest1() {
        String url = "http://localhost:8081/postTest1";
        // 模拟表单参数
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("id", 1);
        map.add("name","zhangsan");
        HttpHeaders headers = new HttpHeaders();
        // 组装请求体
        HttpEntity<MultiValueMap<String,Object>> request = new HttpEntity<>(map,headers);
        User user = restTemplate.postForObject(url, request, User.class);
        return user;

    }

    /**
     * 表单传递对象
     *     @PostMapping("postTest2")
     *     public User postTest2(User user) {
     * @return
     */
    @GetMapping(value = "postTest2")
    public User postTest2() {
        String url = "http://localhost:8081/postTest2";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("id",2);
        map.add("name","lisi");
        HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map,headers);
        User user = restTemplate.postForObject(url, request, User.class);
        return user;
    }

    /**
     * 发送方传递对象,接收方使用@RequestBody+对象接收
     * @return
     */
    @GetMapping("postTest3")
    public User postTest3(){
        String url = "http://localhost:8081/postTest3";
        User wangwu = User.builder().id(3).name("wangwu").build();
        User user = restTemplate.postForObject(url, wangwu, User.class);
        return user;
    }

    /**
     * 发送方传递map参数 接收方使用@RequestBody+对象接收
     * @return
     */
    @GetMapping("postTest4")
    public User postTest4() {
        String url = "http://localhost:8081/postTest4";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "Eric");
        User result = restTemplate.postForObject(url, map, User.class);
        return result;
    }

    @GetMapping("postTest5")
    public String postTest5(){
        //请求地址
        String url = "http://localhost:8081/testPostByLocation";
        //入参
        User wangwu = User.builder().id(3).name("wangwu").build();
        //用于提交完成数据之后的页面跳转，返回跳转url
        URI uri = restTemplate.postForLocation(url, wangwu);
        System.out.println(uri.toString());
        return uri.toString();
    }

}
