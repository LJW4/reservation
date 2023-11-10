package com.example.reservation.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationHistoryDto {
    private String storeName;
    private String lessonName;
    private String parentName;
    private String email;
    private int capacity;
    private LocalDate reservationDate;
}
