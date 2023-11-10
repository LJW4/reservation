package com.example.reservation.exception;

public record ResponseStatusExceptionDto(
        String error,
        String path,
        String message,
        int status,
        String timestamp
) {
}
