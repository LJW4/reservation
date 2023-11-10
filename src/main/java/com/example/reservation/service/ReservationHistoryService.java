package com.example.reservation.service;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.dto.ReservationHistoryDto;
import com.example.reservation.repository.ReservationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationHistoryService {
    private final ReservationHistoryRepository reservationHistoryRepository;

    @Transactional(readOnly = true)
    public List<ReservationHistoryDto> findHistoriesByParentId(long parentId) {
        return reservationHistoryRepository.findAllByParentId(parentId);
    }
}
