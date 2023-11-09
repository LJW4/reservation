package com.example.reservation.service;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.entity.ReservationHistory;
import com.example.reservation.repository.ReservationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReservationHistoryService {
    private final ReservationHistoryRepository reservationHistoryRepository;

    @Transactional(readOnly = true)
    public ReservationDto findReservation(LocalDate reservationTime) {
        reservationHistoryRepository.findByCreatedDate(reservationTime);
        return null;
    }
}
