package com.flightapp.flightticketsystem.controller;

import com.flightapp.flightticketsystem.dto.SeatRequestDto;
import com.flightapp.flightticketsystem.dto.SeatResponseDto;
import com.flightapp.flightticketsystem.service.ISeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seats")
@RequiredArgsConstructor
public class SeatController {

    private final ISeatService seatService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add/seats")
    public SeatResponseDto addSeat(@RequestBody SeatRequestDto requestDto) {
        return seatService.addSeat(requestDto);
    }

    @GetMapping("/flight/{flightId}")
    public List<SeatResponseDto> getSeatsByFlightId(@PathVariable Integer flightId) {
        return seatService.getSeatsByFlightId(flightId);
    }

    @GetMapping("/seats/available/{flightId}")
    public List<SeatResponseDto> getAvailableSeatsByFlightId(@PathVariable Integer flightId) {
        return seatService.getAvailableSeatsByFlightId(flightId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/seats/{id}")
    public SeatResponseDto updateSeat(@PathVariable Integer id, @RequestBody SeatRequestDto requestDto) {
        return seatService.updateSeat(id, requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteSeat(@PathVariable Integer id) {
        seatService.deleteSeat(id);
    }
}
