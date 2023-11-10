package com.example.reservation.repository.querydsl.impl;

import com.example.reservation.dto.ReservationHistoryDto;
import com.example.reservation.dto.request.ParentHistoryDto;
import com.example.reservation.entity.QLesson;
import com.example.reservation.entity.QParent;
import com.example.reservation.entity.QReservationHistory;
import com.example.reservation.repository.querydsl.ReservationHistoryQueryDslRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.example.reservation.entity.QLesson.lesson;
import static com.example.reservation.entity.QParent.parent;
import static com.example.reservation.entity.QReservationHistory.reservationHistory;

@Repository
@RequiredArgsConstructor
public class ReservationHistoryQueryDslRepositoryImpl implements ReservationHistoryQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReservationHistoryDto> findAllMadeByQueryDsl(Long parentId, Long lessonId, LocalDate reservationDate) {
        return queryFactory
                .select(
                        Projections.constructor(
                                ReservationHistoryDto.class,
                                lesson.store.storeName,
                                lesson.lessonName,
                                parent.parentName,
                                parent.email,
                                reservationHistory.capacity,
                                reservationHistory.reservationDate
                        )
                )
                .from(reservationHistory)
                .join(reservationHistory.parent, parent)
                .join(reservationHistory.lesson, lesson)
                .where(
                        searchBuilder(parentId, lessonId, reservationDate)
                )
                .fetch();
    }

    private BooleanBuilder searchBuilder(Long parentId, Long lessonId, LocalDate reservationDate) {
        BooleanBuilder builder = new BooleanBuilder();
        if (parentId != null) {
            builder.and(parent.parentId.eq(parentId));
        }
        if (lessonId != null) {
            builder.and(lesson.lessonId.eq(lessonId));
        }
        if (reservationDate != null) {
            builder.and(reservationHistory.reservationDate.eq(reservationDate));
        }
        return builder;
    }
}
