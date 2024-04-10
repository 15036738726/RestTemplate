package com.example.controller.exchange;

import com.example.domain.Result;
import com.example.domain.User;
import com.example.util.ExUtil;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工具类调用测试
 */
@RestController
public class ExUtilTest {
    @Resource
    private ExUtil exUtil;

    // myExchangeGeneralType  get
    @GetMapping("utilGetTest")
    public String utilGetTest(){
        String url = "http://localhost:8081/getUser?name=zs";
        User result = exUtil.myExchangeGeneralType(HttpMethod.GET, url, new HttpHeaders(),null , User.class);
        return result.toString();
    }

    // myExchangeGeneralType  post
    @GetMapping("utilPostTest")
    public User utilPostTest(){
        String url = "http://localhost:8081/testPostUser";
        User user = User.builder().id(111).name("util").build();
        User result = exUtil.myExchangeGeneralType(HttpMethod.POST, url, new HttpHeaders(),user , User.class);
        return result;
    }


    // myExchangeCustomType  get
    @GetMapping("customutilGetTest")
    public List<Result<User>> customutilGetTest(){
        String url = "http://localhost:8081/getResultList?name=zhangsan&id=18";
        ParameterizedTypeReference<List<Result<User>>> type = new ParameterizedTypeReference<List<Result<User>>>() {
        };
        List<Result<User>> results = exUtil.myExchangeCustomType(HttpMethod.GET, url, new HttpHeaders(), null, type);
        return results;
    }

    // myExchangeCustomType post
    @GetMapping("customutilPostTest")
    public Result<User> customutilPostTest(){
        String url = "http://localhost:8081/testPostResult";
        User user = User.builder().id(5).name("张三丰").build();
        ParameterizedTypeReference<Result<User>> responseBodyType = new ParameterizedTypeReference<Result<User>>() {
        };
        Result<User> result = exUtil.myExchangeCustomType(HttpMethod.POST, url, new HttpHeaders(), user, responseBodyType);
        return result;
    }

}
