package com.example.controller.exchange;

import com.example.domain.Result;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class ExchangeTest {

    @Autowired
    private RestTemplate restTemplate;
    // https://blog.csdn.net/scm_2008/article/details/127682333
    /**
     *  #处理复杂类型的返回值  List<User>  List<String>
     * 	public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
     * 			ParameterizedTypeReference<T> responseType)
     *
     *  #处理简单类型返回值  String  Bean 这种
     *	public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
     *		Class<T> responseType)
     *
     * 参数	说明
     * url	调用的url地址
     * method	枚举值，HttpMethod：GET、POST、PUT、DELETE等
     * requestEntity	发起请求时携带的对象：请求头header 和/或 请求体body
     * responseType	请求响应对象的类型
     * uriVariables	就是针对url中的@PathVariable参数，可变长度参数列表
     *
     */


    /////////////////GET测试////////////////////


    /**
     * 1.1返回基本类型
     * @return
     */
    @GetMapping("get")
    public String get() {
        String url = "http://localhost:8081/get?name=zs";
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return exchange.getBody();
    }

    /**
     * 1.2 返回自定义对象类型   responseType是Class<T>
     * @return
     */
    @GetMapping("getUser")
    public User getUser() {
        String url = "http://localhost:8081/getUser?name=zs";
        ResponseEntity<User> exchange = restTemplate.exchange(url, HttpMethod.GET, null, User.class);
        return exchange.getBody();
    }

    /**
     * // 1.3 get请求返回List<T>类型  通过ParameterizedTypeReference指定返回的List
     * @return
     */
    @GetMapping("getTypeRtnList")
    public List<User> getUserList() {
        String url = "http://localhost:8081/getTypeRtnList?name=zhangsan";
        ParameterizedTypeReference<List<User>> responseBodyType = new ParameterizedTypeReference<List<User>>(){

        };
        ResponseEntity<List<User>> exchange = restTemplate.exchange(url, HttpMethod.GET, null, responseBodyType);
        return exchange.getBody();
    }

    /**
     * 1.4 返回Map 类型
     * @return
     */
    @GetMapping("getMap")
    public Map<String,Object> getMap(){
        String url = "http://localhost:8081/getMap?key=zhangsan&val=18";
        ParameterizedTypeReference<Map<String,Object>> responseBodyType = new ParameterizedTypeReference<Map<String, Object>>() {

        };
        ResponseEntity<Map<String, Object>> exchange = restTemplate.exchange(url, HttpMethod.GET, null, responseBodyType);
        return exchange.getBody();
    }

    /**
     * 1.5返回自定义泛型类型
     * @return
     */
    @GetMapping("getCustomType")
    public Result<User> getCustomType() {
        String url = "http://localhost:8081/getCustomType?name=zhangsan&id=18";
        ParameterizedTypeReference<Result<User>> responseBodyType = new ParameterizedTypeReference<Result<User>>() {

        };
        ResponseEntity<Result<User>> exchange = restTemplate.exchange(url, HttpMethod.GET, null, responseBodyType);
        return exchange.getBody();
    }

    /**
     * 1.6返回List<自定义类型>  跟1.3那个差不多 无论多复杂的类型 ParameterizedTypeReference这里都可以指定
     * @return
     */
    @GetMapping("getResultList")
    public List<Result<User>> getResultList(){
        String url = "http://localhost:8081/getResultList?name=zhangsan&id=18";
        ParameterizedTypeReference<List<Result<User>>> responseBodyType = new ParameterizedTypeReference<List<Result<User>>>() {

        };
        ResponseEntity<List<Result<User>>> exchange = restTemplate.exchange(url, HttpMethod.GET, null, responseBodyType);
        return exchange.getBody();
    }
    /////////////////GET测试////////////////////




    /////////////////POST测试////////////////////

    /**
     * 2.1传header+body返回对象类型
     * @return
     */
    @GetMapping("testPostUser")
    public User testPostUser(){
        String url = "http://localhost:8081/testPostUser";
        HttpHeaders headers = new HttpHeaders();
        // 设置参数类型,默认是application/json 可以不用设置
        headers.add("Content-type","application/json");
        // 设置请求体参数
        User body = User.builder().id(1).name("王维").build();
        HttpEntity<?> entity = new HttpEntity<>(body,headers);
        // 发送
        ResponseEntity<User> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, User.class);
        return exchange.getBody();
    }

    /**
     * 2.2 传header+body返回自定义泛型类型
     * @return
     */
    @GetMapping("testPostResult")
    public Result<User> testPostResult(){
        String url = "http://localhost:8081/testPostResult";
        HttpHeaders headers = new HttpHeaders();
        User user = User.builder().id(5).name("张三丰").build();
        HttpEntity<?> entity = new HttpEntity<>(user,headers);
        ParameterizedTypeReference<Result<User>> responseBodyType = new ParameterizedTypeReference<Result<User>>() {
        };
        ResponseEntity<Result<User>> exchange = restTemplate.exchange(url, HttpMethod.POST, entity, responseBodyType);
        return exchange.getBody();
    }

    /////////////////POST测试////////////////////



}
