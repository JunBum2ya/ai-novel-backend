package com.midas.novel.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final String code;
    private final String message;

    public CustomException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public static CustomException of(CustomExceptionType customExceptionType) {
        return new CustomException(customExceptionType.getCode(), customExceptionType.getMessage());
    }

}
