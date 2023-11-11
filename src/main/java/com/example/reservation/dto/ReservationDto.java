package com.example.reservation.dto;

import com.example.reservation.enumeration.ReservationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

public class ReservationDto {
    public static class Request {
        @Schema(name = "ReservationDto.Request.LessonReservation", description = "수업 예약")
        public record LessonReservation(
                @Schema(title = "부모 ID", example = "1")
                Long parentId,
                @Schema(title = "인원 수", example = "3")
                Integer capacity,
                @Schema(title = "수업 ID", example = "1")
                Long lessonId,
                @Schema(title = "예약 일자", example = "2023-11-20")
                LocalDate reservationDate
        ) {
        }
    }

    public static class Response {
        @Schema(name = "ReservationDto.Response.ReservationHistory", description = "예약자, 예약 이력 확인")
        public record ReservationHistory(
                @Schema(title = "지점 명", example = "강남점")
                String storeName,
                @Schema(title = "수업 명", example = "도시농부")
                String lessonName,
                @Schema(title = "부모 명", example = "홍길동")
                String parentName,
                @Schema(title = "부모 이메일", example = "parent@gmail.com")
                String email,
                @Schema(title = "예약 인원 수", example = "3")
                int capacity,
                @Schema(title = "예약 일자", example = "2023-11-20")
                LocalDate reservationDate,
                @Schema(title = "예약 상태", example = "RESERVE")
                ReservationStatus reservationStatus
        ) {
        }
    }
}
