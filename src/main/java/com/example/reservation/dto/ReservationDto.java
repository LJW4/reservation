package com.example.reservation.dto;

import lombok.*;

import java.time.LocalDate;

public class ReservationDto {

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LessonReservation {
        private Long parentId;
        private Integer capacity;
        private Long storeId;
        private Long lessonId;
        private LocalDate reservationDate;
    }

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
    }
}
