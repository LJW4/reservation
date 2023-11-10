package com.example.reservation.service;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.entity.Lesson;
import com.example.reservation.entity.Parent;
import com.example.reservation.entity.ReservationHistory;
import com.example.reservation.enumeration.ReservationStatus;
import com.example.reservation.repository.LessonRepository;
import com.example.reservation.repository.ParentRepository;
import com.example.reservation.repository.ReservationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final LessonRepository lessonRepository;
    private final ParentRepository parentRepository;
    private final ReservationHistoryRepository reservationHistoryRepository;

    @Transactional
    public void lessonReservation(ReservationDto.Request.LessonReservation lessonReservationDto) {
        Parent findParent = parentRepository.findById(lessonReservationDto.getParentId())
                .orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다."));

        Lesson findLesson = lessonRepository.findById(lessonReservationDto.getLessonId())
                .orElseThrow(() -> new RuntimeException("수업 정보를 찾을 수 없습니다."));

        // 예약 여부 확인, 동일 날짜에 동일 수업 중복 예약 불가
        hasReservation(lessonReservationDto, findParent, findLesson);

        // 수업 인원이 초과할 경우 예약 불가
        checkCapacity(lessonReservationDto, findLesson);

        reservationHistoryRepository.save(
                ReservationHistory.builder()
                        .parent(findParent)
                        .lesson(findLesson)
                        .capacity(lessonReservationDto.getCapacity())
                        .reservationDate(lessonReservationDto.getReservationDate())
                        .reservationStatus(ReservationStatus.RESERVE)
                        .build()
        );
    }

    private void checkCapacity(ReservationDto.Request.LessonReservation lessonReservationDto, Lesson findLesson) {
        List<ReservationHistory> findReservations = reservationHistoryRepository.findByLessonAndReservationDateAndReservationStatus(
                findLesson, lessonReservationDto.getReservationDate(), ReservationStatus.RESERVE
        );

        int currentCapacity = findReservations.stream()
                .mapToInt(ReservationHistory::getCapacity)
                .sum();

        if ((currentCapacity + lessonReservationDto.getCapacity()) > findLesson.getMaxCapacity()) {
            throw new RuntimeException("예약 가능한 인원을 초과하였습니다.");
        }
    }

    private void hasReservation(ReservationDto.Request.LessonReservation lessonReservationDto, Parent findParent, Lesson findLesson) {
        Boolean hasReservation = reservationHistoryRepository.existsByParentAndLessonAndReservationDate(
                findParent, findLesson, lessonReservationDto.getReservationDate()
        );

        if (hasReservation) {
            throw new RuntimeException("해당 날짜에 이미 예약이 있습니다.");
        }
    }
}
