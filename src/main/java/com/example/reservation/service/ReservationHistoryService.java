package com.example.reservation.service;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.repository.ReservationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationHistoryService {
    private final ReservationHistoryRepository reservationHistoryRepository;
}
