package com.example.reservation.controller;

import com.example.reservation.dto.ReservationDto;
import com.example.reservation.exception.ResponseStatusExceptionDto;
import com.example.reservation.service.ReservationService;
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

@Tag(name = "예약 API")
@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @Operation(summary = "수업 예약", description = "수업을 예약 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "4xx, 5xx", description = "fail", content = @Content(schema = @Schema(implementation = ResponseStatusExceptionDto.class)))
    })
    @PostMapping("/api/reservation")
    public ResponseEntity<String> reservation(@RequestBody ReservationDto.Request.LessonReservation lessonReservationDto) {
        reservationService.reservation(lessonReservationDto);
        return ResponseEntity.ok("예약이 완료되었습니다.");
    }

    @Operation(summary = "예약 취소", description = "예약을 취소 합니다.", parameters = {
            @Parameter(name = "reservationId", required = true, example = "1", description = "예약 내역 ID"),
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "4xx, 5xx", description = "fail", content = @Content(schema = @Schema(implementation = ResponseStatusExceptionDto.class)))
    })
    @PatchMapping("/api/reservation/{reservationId}/cancel")
    public ResponseEntity<String> reservationCancel(@PathVariable("reservationId") long reservationId) {
        reservationService.reservationCancel(reservationId);
        return ResponseEntity.ok("예약을 취소하였습니다.");
    }
}
