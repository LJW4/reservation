package com.example.reservation.entity;

import com.example.reservation.enumeration.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "t_reservation_history")
public class ReservationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Comment("인원수")
    @Column(name = "capacity")
    private int capacity;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_status")
    private ReservationStatus reservationStatus;

    public static ReservationHistory create(Parent parent, Lesson lesson, int capacity, LocalDate reservationDate, ReservationStatus reservationStatus) {
        ReservationHistory reservationHistory = new ReservationHistory();
        reservationHistory.parent = parent;
        reservationHistory.lesson = lesson;
        reservationHistory.capacity = capacity;
        reservationHistory.reservationDate = reservationDate;
        reservationHistory.reservationStatus = reservationStatus;
        return reservationHistory;
    }

    public void changeStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}
