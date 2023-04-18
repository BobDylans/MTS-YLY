package com.example.mtsyl.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author 20179
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private Integer code;
    private String message;
    private T data;


    public static<T> Response<T> success() {
        return new Response<>(200,"成功",null);
    }


    public Response<T> setSuccessCode(Integer code) {
        this.setCode(code);
        return this;
    }
    public  Response<T> setSuccessMessage(String message) {
        this.setMessage(message);
        return this;
    }
    public Response<T> setSuccessData(T data) {
        this.setData(data);
        return this;
    }
    public static<T>Response<T> fail() {
        return new Response<>(500,"失败",null);
    }
    public Response<T> setFailMessage(String message) {
        this.setMessage(message);
        return this;
    }
    public Response<T> setFailCode(Integer code) {
        this.setCode(code);
        return this;
    }
}
