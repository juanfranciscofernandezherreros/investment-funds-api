package com.example.investmentfunds.common.exception;

import org.springframework.http.HttpStatus;

public enum AppError {
    FUND_NOT_FOUND(HttpStatus.NOT_FOUND, "Fund not found"),
    ISIN_ALREADY_EXISTS(HttpStatus.CONFLICT, "ISIN already exists");
    private final HttpStatus status;
    private final String message;

    AppError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
