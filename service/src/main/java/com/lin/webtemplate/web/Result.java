package com.lin.webtemplate.web;

/**
 * Simple HTTP response wrapper: { code, message, data }.
 */
public class Result<T>(int code, String message, T data) {

    public static <T> Result<T> ok(T data) {
        return new Result<>(0, "OK", data);
    }

    public static <T> Result<T> fail(int code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
