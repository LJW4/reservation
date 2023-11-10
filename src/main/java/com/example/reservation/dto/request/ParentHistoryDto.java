package com.example.reservation.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentHistoryDto {
    private Long lessonId;
    private LocalDate reservationDate;
}
