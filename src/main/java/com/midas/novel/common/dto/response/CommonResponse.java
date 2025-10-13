package com.midas.novel.common.dto.response;

import com.midas.novel.common.exception.CustomExceptionType;
import lombok.Getter;

@Getter
public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;

    public CommonResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResponse<T> of(CustomExceptionType customExceptionType) {
        return new CommonResponse<T>(customExceptionType.getCode(), customExceptionType.getMessage(), null);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<T>(CustomExceptionType.SUCCESS.getCode(), CustomExceptionType.SUCCESS.getMessage(), data);
    }

}
