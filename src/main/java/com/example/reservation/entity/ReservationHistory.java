package com.example.reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "t_reservation_history", indexes = {
        @Index(name = "idx_parent_id_lesson_id", columnList = "parent_id, lesson_id")
})
public class ReservationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "email")
    private String email;

    @Column(name = "lesson_id")
    private Long lessonId;

    @Column(name = "lesson_name")
    private String lessonName;

    @Comment("인원수")
    @Column(name = "capacity")
    private int capacity;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;
}
