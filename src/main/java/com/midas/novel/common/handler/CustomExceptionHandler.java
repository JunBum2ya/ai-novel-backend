package com.midas.novel.common.handler;

import com.midas.novel.common.dto.response.CommonResponse;
import com.midas.novel.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponse<String>> handleCustomException(CustomException e) throws CustomException {
        log.error(e.getMessage());
        CommonResponse<String> response = new CommonResponse<>(e.getCode(), e.getMessage(), null);
        return ResponseEntity.badRequest().body(response);
    }

}
