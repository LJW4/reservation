package com.example.reservation.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDto {
    private String parentName;
    private String lessonName;
    private int capacity;
    private LocalDate reservationTime;
}
