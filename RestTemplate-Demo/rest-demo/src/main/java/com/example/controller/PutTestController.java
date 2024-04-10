package com.example.controller;

import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PutTestController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 正常情况put 没有返回值 在规范中  post(新增) 非幂等;
     *  get put(更新) delete是幂等性操作
     *  所以看情况吧,如果需要更新之后的返回值  可以使用post来进行更新
     */
    @GetMapping("putTest1")
    public void putTest1(){
        String url = "http://localhost:8081/putTest1";
        User wangwu = User.builder().id(3).name("wangwu").build();
        restTemplate.put(url,wangwu);



    }

    /**
     * 使用exchange  获取返回值
     * @return
     */
    @GetMapping("putTest2")
    public User putTest2(){
        String url = "http://localhost:8081/putTest2";
        HttpHeaders headers = new HttpHeaders();
        // headers.add("Content-Type", "application/json");
        User zhaoliu = User.builder().id(3).name("zhaoliu").build();
        HttpEntity<?> requestEntity = new HttpEntity<>(zhaoliu, headers);
        ResponseEntity<User> exchange = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, User.class);
        User body = exchange.getBody();
        // System.out.println(body);
        return body;
    }


}
