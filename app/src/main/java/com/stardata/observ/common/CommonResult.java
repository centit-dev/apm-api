package com.stardata.observ.common;

import lombok.Getter;

import org.springframework.http.HttpStatus;


@Getter
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;

    protected CommonResult() {
    }

    protected CommonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(HttpStatus.OK.value(), message, data);
    }

    public static <T> CommonResult<T> failed(HttpStatus errorCode) {
        return new CommonResult<T>(errorCode.value(), errorCode.getReasonPhrase(), null);
    }

    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null);
    }

    public static <T> CommonResult<T> failed() {
        return failed(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> CommonResult<T> validateFailed() {
        return failed(HttpStatus.NOT_FOUND);
    }

    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(HttpStatus.NOT_FOUND.value(), message, null);
    }

    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), data);
    }

    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), data);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
