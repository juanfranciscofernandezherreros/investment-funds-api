package com.example.investmentfunds.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handle(AppException e) {
        log.warn("[FUND] - ACTION: request failed: error: {}", e.getError().name());
        return ResponseEntity.status(e.getError().getStatus())
                .body(new ErrorResponse(e.getError().name(), e.getMessage()));
    }
}
