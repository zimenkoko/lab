package ru.zirobobrik.studying.zimenkoHAHAbot.exception;

import lombok.*;

@Getter
@Setter
public class AppError {
    private int statusCode;
    private String message;

    public AppError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public AppError(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public AppError() {
    }
}