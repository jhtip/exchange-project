package com.country.exchange.exception;

import com.country.exchange.dto.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = {RestController.class})
public class ApiExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public ResponseEntity<Object> handleBusinessException(BusinessException e) {
        log.error("{}", e.getErr());
        Err err = e.getErr();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, err.code(), err.message());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
