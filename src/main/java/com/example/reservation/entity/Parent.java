package com.example.reservation.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "t_parent")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long parentId;

    @Comment("부모님 이름")
    @Column(name = "parent_name", nullable = false)
    private String parentName;

    @Comment("이메일 주소")
    @Column(name = "email")
    private String email;

    public static Parent create(String parentName, String email) {
        Parent parent = new Parent();
        parent.parentName = parentName;
        parent.email = email;
        return parent;
    }
}
