package com.example.reservation;

import com.example.reservation.repository.ReservationHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
public class ReservationTests {
    @Autowired
    private ReservationHistoryRepository reservationHistoryRepository;

    @Test
    @Transactional
    public void findReservationTest() {
        reservationHistoryRepository.findByCreatedDate(LocalDate.now());
    }
}
