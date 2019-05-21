package com.country.exchange.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private HttpStatus status;
    private String code;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String code, String message) {
        this();
        this.status = status;
        this.code = code;
        this.message = message;
    }
}