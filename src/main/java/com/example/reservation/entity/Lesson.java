package com.example.reservation.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long lessonId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Comment("수업명")
    @Column(name = "lesson_name", nullable = false, length = 20)
    private String lessonName;

    @Comment("최대 인원수")
    @Column(name = "max_capacity")
    private int maxCapacity;

    public static Lesson create(Store store, String lessonName, int maxCapacity) {
        Lesson lesson = new Lesson();
        lesson.store = store;
        lesson.lessonName = lessonName;
        lesson.maxCapacity = maxCapacity;
        return lesson;
    }
}
