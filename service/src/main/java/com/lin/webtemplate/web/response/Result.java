package com.lin.webtemplate.web.response;

import lombok.Data;

/**
 * Simple HTTP response wrapper: { code, message, data }.
 *
 * Note: keep this as a regular class (not a record) to match project preference.
 */
@Data
public class Result<T> {

    private final int code;
    private final String message;
    private final T data;


    public static <T> Result<T> ok(T data) {
        return new Result<>(0, "OK", data);
    }

    public static <T> Result<T> fail(int code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
