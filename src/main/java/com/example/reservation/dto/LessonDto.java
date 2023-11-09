package com.example.reservation.dto;

import lombok.*;

import java.time.LocalDate;

public class LessonDto {

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Reserve {
        private Long parentId;
        private Integer capacity;
        private Long storeId;
        private Long lessonId;
        private LocalDate reservationTime;
    }
}
