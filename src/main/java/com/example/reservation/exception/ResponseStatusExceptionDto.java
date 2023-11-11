package com.example.reservation.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ResponseStatusExceptionDto", description = "예외 메시지")
public record ResponseStatusExceptionDto(
        @Schema(title = "error", example = "BAD_REQUEST")
        String error,
        @Schema(title = "path", example = "http://localhost:8080/api/reservation")
        String path,
        @Schema(title = "message", example = "당일 기준 14일 까지만 예약할 수 있습니다.")
        String message,
        @Schema(title = "status", example = "400")
        int status,
        @Schema(title = "timestamp", example = "2023-11-11 09:52:10")
        String timestamp
) {
}
