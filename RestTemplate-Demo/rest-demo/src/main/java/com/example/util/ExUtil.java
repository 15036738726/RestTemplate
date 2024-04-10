package com.example.util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 对exchange方法进行封装,然后处理调用时出现的异常类型
 * 一共2个工具方法,普通类型 和 泛型类型
 */
@Component
public class ExUtil {
    // myExchangeGeneralType
    // myExchangeCustomType

    @Resource
    private RestTemplate restTemplate;

    public  <T> T myExchangeGeneralType (HttpMethod httpMethod, String url, HttpHeaders headers, Object data
            , Class<T> responseType){
        HttpEntity<?> requestEntity = null;
        if (headers != null || data != null) {
            requestEntity = new HttpEntity<>(data, headers);
        }
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, responseType);
            if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
                return responseEntity.getBody();
            }else {
                // 处理Code不等于200的情况, 这里只简单打印，你需要根据你们项目的情况修改合适的处理方式
                System.out.println("返回结果不等于200：code=" + responseEntity.getStatusCode().value()
                        + " reason=" + responseEntity.getStatusCode().getReasonPhrase());
            }
        } catch (RestClientException e) {
            // 处理RestClientException, 这里只简单打印
            e.printStackTrace();
        }
        return null;
    }

    public  <T> T myExchangeCustomType (HttpMethod httpMethod, String url, HttpHeaders headers, Object data
            , ParameterizedTypeReference<T> responseType){
        HttpEntity<?> requestEntity = null;
        if (headers != null || data != null) {
            requestEntity = new HttpEntity<>(data, headers);
        }
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity, responseType);
            if(responseEntity.getStatusCode().equals(HttpStatus.OK)){
                return responseEntity.getBody();
            }else {
                // 处理Code不等于200的情况, 这里只简单打印，你需要根据你们项目的情况修改合适的处理方式
                System.out.println("返回结果不等于200：code=" + responseEntity.getStatusCode().value()
                        + " reason=" + responseEntity.getStatusCode().getReasonPhrase());
            }
        } catch (RestClientException e) {
            // 处理RestClientException, 这里只简单打印
            e.printStackTrace();
        }
        return null;
    }


}
