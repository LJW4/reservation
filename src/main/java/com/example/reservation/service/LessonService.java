package com.example.reservation.service;

import com.example.reservation.dto.LessonDto;
import com.example.reservation.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    @Transactional
    public void reserveLesson(LessonDto.Reserve reserveDto) {

    }
}
