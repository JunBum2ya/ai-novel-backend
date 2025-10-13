package com.midas.novel.common.exception;

import lombok.Getter;

@Getter
public enum CustomExceptionType {
    SUCCESS("000", "SUCCESS"),
    ACCESS_DENIED("100", "ACCESS_DENIED"),
    NOT_EXISTED_ENTITY("101", "NOT_EXISTED_ENTITY"),
    SERVER_ERROR("102", "SERVER_ERROR"),;

    private final String code;
    private final String message;

    CustomExceptionType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
