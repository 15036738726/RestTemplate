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
public class DeleteTestController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 不携带参数 无返回值
     */
    @GetMapping("delTest1")
    public void delTest1(){
        String url = "http://localhost:8081/delTest1";
        restTemplate.delete(url);
    }

    /**
     * RestTemplate 的 delete 方法并不支持传入请求体（Request Body 可以通过uri路径的方式传参  ,同样也没有返回值
     */
    @GetMapping("delTest2")
    public void delTest2(){
        String url = "http://localhost:8081/delTest2/1";
        restTemplate.delete(url);
    }

    /**
     * 使用exchange  获取返回值
     * @return
     */
    @GetMapping("delTest3")
    public User delTest3(){
        String url = "http://localhost:8081/delTest3";
        HttpHeaders headers = new HttpHeaders();
        User zhaoliu = User.builder().id(3).name("zhaoliu").build();
        HttpEntity<?> requestEntity = new HttpEntity<>(zhaoliu, headers);
        ResponseEntity<User> exchange = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, User.class);
        return  exchange.getBody();
    }

}
