package com.example.reservation;

import com.example.reservation.entity.Lesson;
import com.example.reservation.entity.Parent;
import com.example.reservation.entity.Store;
import com.example.reservation.repository.LessonRepository;
import com.example.reservation.repository.ParentRepository;
import com.example.reservation.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class ReservationApplicationTests {
	@Autowired
	private LessonRepository lessonRepository;

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Test
	@Transactional
	@Rollback(value = false)
	void contextLoads() {
		Store savedStore = storeRepository.save(
				Store.builder()
						.storeName("강남점")
						.postNumber("12345")
						.address("서울특별시 강남구 강남대로 4")
						.detailAddress("강남 빌딩 14층")
						.build()
		);
		lessonRepository.saveAll(
				IntStream.iterate(1, i -> i + 1)
						.limit(4)
						.mapToObj(i -> Lesson.builder()
								.store(savedStore)
								.lessonName("놀이" + i)
								.maxCapacity(20)
								.build()
						)
						.collect(Collectors.toList())
		);
		parentRepository.save(
				Parent.builder()
						.parentName("부모1")
						.email("parent@gmail.com")
						.build()
		);
	}

}
