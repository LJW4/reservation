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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final LessonRepository lessonRepository;
    private final ParentRepository parentRepository;
    private final ReservationHistoryRepository reservationHistoryRepository;

    @Transactional
    public void reservation(ReservationDto.Request.LessonReservation lessonReservationDto) {
        LocalDate today = LocalDate.now();
        LocalDate maxDay = today.plusDays(14);
        LocalDate reservationDate = lessonReservationDto.reservationDate();

        // 예약 날짜 확인
        // 당일 예약 불가, 당일 이전 예약 불가, 당일 기준 14일 까지만 예약 가능
        checkDate(today, maxDay, reservationDate);

        Parent findParent = parentRepository.findById(lessonReservationDto.parentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자 정보를 찾을 수 없습니다."));

        Lesson findLesson = lessonRepository.findById(lessonReservationDto.lessonId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "수업 정보를 찾을 수 없습니다."));

        // 예약 여부 확인, 동일 날짜에 동일 수업 중복 예약 불가
        hasReservation(lessonReservationDto, findParent, findLesson);

        // 수업 인원이 초과할 경우 예약 불가
        checkCapacity(lessonReservationDto, findLesson);

        reservationHistoryRepository.save(
                ReservationHistory.create(
                        findParent, findLesson, lessonReservationDto.capacity(),
                        lessonReservationDto.reservationDate(), ReservationStatus.RESERVE
                )
        );
    }

    @Transactional
    public void reservationCancel(long reservationId) {
        ReservationHistory findReservation = reservationHistoryRepository.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "예약 정보를 찾을 수 없습니다."));
        findReservation.changeStatus(ReservationStatus.CANCEL);
    }

    private static void checkDate(LocalDate today, LocalDate maxDay, LocalDate reservationDate) {
        if (today.isEqual(reservationDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "당일 예약은 불가능 합니다.");
        }
        if (today.isAfter(reservationDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이전 날짜 예약은 불가능 합니다.");
        }
        if (maxDay.isBefore(reservationDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "당일 기준 14일 까지만 예약할 수 있습니다.");
        }
    }

    private void checkCapacity(ReservationDto.Request.LessonReservation lessonReservationDto, Lesson findLesson) {
        List<ReservationHistory> findReservations = reservationHistoryRepository.findByLessonAndReservationDateAndReservationStatus(
                findLesson, lessonReservationDto.reservationDate(), ReservationStatus.RESERVE
        );

        int currentCapacity = findReservations.stream()
                .mapToInt(ReservationHistory::getCapacity)
                .sum();

        if ((currentCapacity + lessonReservationDto.capacity()) > findLesson.getMaxCapacity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "예약 가능한 인원을 초과하였습니다.");
        }
    }

    private void hasReservation(ReservationDto.Request.LessonReservation lessonReservationDto, Parent findParent, Lesson findLesson) {
        Boolean hasReservation = reservationHistoryRepository.existsByParentAndLessonAndReservationDateAndReservationStatus(
                findParent, findLesson, lessonReservationDto.reservationDate(), ReservationStatus.RESERVE
        );

        if (hasReservation) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 날짜에 이미 예약이 있습니다.");
        }
    }
}
