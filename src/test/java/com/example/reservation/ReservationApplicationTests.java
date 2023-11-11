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
				Store.create(
						"강남점2",
						"12345",
						"서울특별시 강남구 강남대로 5",
						"강남 빌딩 11층"
				)
		);
		lessonRepository.saveAll(
				IntStream.iterate(1, i -> i + 1)
						.limit(4)
						.mapToObj(i -> Lesson.create(savedStore, "놀이" + i, 20))
						.collect(Collectors.toList())
		);
		parentRepository.saveAll(
				IntStream.iterate(1, i -> i + 1)
						.limit(10)
						.mapToObj(i -> Parent.create("부모" + i, "parent" + i + "@gmail.com"))
						.collect(Collectors.toList())
		);
	}

}
