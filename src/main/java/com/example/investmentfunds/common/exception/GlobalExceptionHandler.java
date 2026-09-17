package com.example.investmentfunds.common.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(ConstraintViolationException exception) {
        log.warn("[FUND] - ACTION: validation failed: {}", exception.getMessage());
        return new ErrorResponse("VALIDATION_ERROR", exception.getMessage());
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handle(AppException e) {
        log.warn("[FUND] - ACTION: request failed: error: {}", e.getError().name());
        return ResponseEntity.status(e.getError().getStatus())
                .body(new ErrorResponse(e.getError().name(), e.getMessage()));
    }
}
