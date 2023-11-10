package com.example.reservation.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<?> handleResponseStatusException(ResponseStatusException e, HttpServletRequest request) {
        ResponseStatusExceptionDto exceptionDto = new ResponseStatusExceptionDto(
                HttpStatus.valueOf(e.getStatusCode().value()).name(),
                request.getRequestURL().toString(),
                e.getReason(),
                e.getStatusCode().value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        return ResponseEntity.status(e.getStatusCode()).body(exceptionDto);
    }

    @ExceptionHandler
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ResponseStatusExceptionDto exceptionDto = new ResponseStatusExceptionDto(
                HttpStatus.valueOf(e.getStatusCode().value()).name(),
                request.getRequestURL().toString(),
                e.getBindingResult().getFieldError() == null ? "not found error" : e.getBindingResult().getFieldError().getDefaultMessage(),
                e.getStatusCode().value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
        return ResponseEntity.status(e.getStatusCode()).body(exceptionDto);
    }
}
