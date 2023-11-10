package com.example.reservation.dto;

import com.example.reservation.enumeration.ReservationStatus;
import lombok.*;

import java.time.LocalDate;

public class ReservationDto {
    public static class Request {
        public record LessonReservation(
                Long parentId,
                Integer capacity,
                Long lessonId,
                LocalDate reservationDate
        ) {
        }
    }

    public static class Response {
        public record ReservationHistory(
                String storeName,
                String lessonName,
                String parentName,
                String email,
                int capacity,
                LocalDate reservationDate,
                ReservationStatus reservationStatus
        ) {
        }
    }
}
