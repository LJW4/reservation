package com.example.reservation.dto;

import com.example.reservation.enumeration.ReservationStatus;
import lombok.*;

import java.time.LocalDate;

public class ReservationDto {
    public static class Request {
        @Getter @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class LessonReservation {
            private Long parentId;
            private Integer capacity;
            private Long lessonId;
            private LocalDate reservationDate;
        }
    }

    public static class Response {
        @Getter @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class ReservationHistory {
            private String storeName;
            private String lessonName;
            private String parentName;
            private String email;
            private int capacity;
            private ReservationStatus reservationStatus;
        }
    }
}
