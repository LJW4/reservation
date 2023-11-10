package com.example.reservation.service;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.repository.ReservationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationHistoryService {
    private final ReservationHistoryRepository reservationHistoryRepository;

    @Transactional(readOnly = true)
    public List<ReservationDto.Response.ReservationHistory> findHistoriesByParent(Long parentId, Long lessonId, LocalDate reservationDate) {
        return reservationHistoryRepository.findAllMadeByQueryDsl(parentId, null, lessonId, reservationDate);
    }

    @Transactional(readOnly = true)
    public List<ReservationDto.Response.ReservationHistory> findHistoriesByStore(Long storeId, Long lessonId, LocalDate reservationDate) {
        return reservationHistoryRepository.findAllMadeByQueryDsl(null, storeId, lessonId, reservationDate);
    }
}
