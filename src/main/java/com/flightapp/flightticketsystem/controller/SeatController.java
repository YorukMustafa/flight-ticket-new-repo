package com.flightapp.flightticketsystem.controller;

import com.flightapp.flightticketsystem.dto.SeatRequestDto;
import com.flightapp.flightticketsystem.dto.SeatResponseDto;
import com.flightapp.flightticketsystem.service.ISeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seats")
@RequiredArgsConstructor
public class SeatController {

    private final ISeatService seatService;

    @PreAuthorize("hasRole('ADMIN')") //Bu metoda sadece yetkilerinde ROLE_ADMIN olan kişiler girebilir diyoruz
    @PostMapping("/add/seats")
    public ResponseEntity<SeatResponseDto> addSeat(@RequestBody SeatRequestDto requestDto) {
        SeatResponseDto response = seatService.addSeat(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<SeatResponseDto>> getSeatsByFlightId(@PathVariable Integer flightId) {
        return ResponseEntity.ok(seatService.getSeatsByFlightId(flightId));
    }

    @GetMapping("/seats/available/{flightId}")
    public ResponseEntity<List<SeatResponseDto>> getAvailableSeatsByFlightId(@PathVariable Integer flightId) {
        return ResponseEntity.ok(seatService.getAvailableSeatsByFlightId(flightId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/seats/{id}")
    public ResponseEntity<SeatResponseDto> updateSeat(@PathVariable Integer id, @RequestBody SeatRequestDto requestDto) {
        return ResponseEntity.ok(seatService.updateSeat(id, requestDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Integer id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }
}

