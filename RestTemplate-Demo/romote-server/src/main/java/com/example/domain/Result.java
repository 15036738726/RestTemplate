package com.example.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T extends Serializable> implements Serializable {
    private boolean success;
    private String code;
    private String message;
    private T data;

    public static <T extends Serializable> Result<T> success(String code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <T extends Serializable> Result<T> success(T data) {
        return success("200", "成功", data);
    }

    public static <T extends Serializable> Result<T> fail(String code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

}
