package com.example.reservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long storeId;

    @Comment("매장명")
    @Column(name = "store_name", nullable = false, length = 10)
    private String storeName;

    @Comment("우편번호, 앞에 0이 들어가는 번호 때문에 varchar로 설정")
    @Column(name = "post_number", nullable = false, length = 5)
    private String postNumber;

    @Comment("주소")
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Comment("상세 주소")
    @Column(name = "detail_address", nullable = false, length = 200)
    private String detailAddress;
}
