package com.example.reservation.controller;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.exception.ResponseStatusExceptionDto;
import com.example.reservation.service.ReservationHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "부모 API")
@RestController
@RequiredArgsConstructor
public class ParentController {
    private final ReservationHistoryService reservationHistoryService;

    @Operation(summary = "예약 이력 확인", description = "부모의 수업 예약 이력을 확인합니다.", parameters = {
            @Parameter(name = "parentId", required = true, example = "1", description = "부모 ID"),
            @Parameter(name = "storeId", required = false, example = "1", description = "지점 ID"),
            @Parameter(name = "lessonId", required = false, example = "1", description = "수업 ID"),
            @Parameter(name = "reservationDate", required = false, example = "2023-11-24", description = "예약 ID")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ReservationDto.Response.ReservationHistory.class))),
            @ApiResponse(responseCode = "4xx, 5xx", description = "fail", content = @Content(schema = @Schema(implementation = ResponseStatusExceptionDto.class)))
    })
    @GetMapping("/api/parent/{parentId}/reservation-histories")
    public ResponseEntity<List<ReservationDto.Response.ReservationHistory>> findReservationHistories(
            @PathVariable("parentId") Long parentId,
            @RequestParam(value = "storeId", required = false) Long storeId,
            @RequestParam(value = "lessonId", required = false) Long lessonId,
            @RequestParam(value = "reservationDate", required = false) LocalDate reservationDate
    ) {
        return ResponseEntity.ok(reservationHistoryService.findHistoriesByParent(parentId, storeId, lessonId, reservationDate));
    }
}
