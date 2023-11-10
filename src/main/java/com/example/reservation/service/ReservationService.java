package com.example.reservation.service;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.entity.Lesson;
import com.example.reservation.entity.Parent;
import com.example.reservation.entity.ReservationHistory;
import com.example.reservation.repository.LessonRepository;
import com.example.reservation.repository.ParentRepository;
import com.example.reservation.repository.ReservationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final LessonRepository lessonRepository;
    private final ParentRepository parentRepository;
    private final ReservationHistoryRepository reservationHistoryRepository;

    @Transactional
    public void lessonReservation(ReservationDto.LessonReservation lessonReservationDto) {
        Parent findParent = parentRepository.findById(lessonReservationDto.getParentId())
                .orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));

        Lesson findLesson = lessonRepository.findById(lessonReservationDto.getLessonId())
                .orElseThrow(() -> new RuntimeException("수업 정보를 찾을 수 없습니다."));

        Boolean hasReservation = reservationHistoryRepository.existsByParentAndLessonAndReservationDate(
                findParent, findLesson, lessonReservationDto.getReservationDate()
        );

        if (hasReservation) {
            throw new RuntimeException("해당 날짜에 이미 예약이 있습니다.");
        }

        reservationHistoryRepository.save(
                ReservationHistory.builder()
                        .parent(findParent)
                        .lesson(findLesson)
                        .capacity(lessonReservationDto.getCapacity())
                        .reservationDate(lessonReservationDto.getReservationDate())
                        .build()
        );
    }
}
