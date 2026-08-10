package com.flightapp.flightticketsystem.service.impl;

import com.flightapp.flightticketsystem.dto.SeatRequestDto;
import com.flightapp.flightticketsystem.dto.SeatResponseDto;
import com.flightapp.flightticketsystem.entities.Flight;
import com.flightapp.flightticketsystem.entities.Seat;
import com.flightapp.flightticketsystem.exception.BaseException;
import com.flightapp.flightticketsystem.mapper.SeatMapper;
import com.flightapp.flightticketsystem.repository.FlightRepository;
import com.flightapp.flightticketsystem.repository.SeatRepository;
import com.flightapp.flightticketsystem.service.ISeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class  SeatServiceImpl implements ISeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;
    private final FlightRepository flightRepository;

    @Override
    public SeatResponseDto addSeat(SeatRequestDto requestDto) {
        Flight flight = flightRepository.findById(requestDto.getFlightId())
                .orElseThrow(() -> new BaseException("error.flight_not_found"));
        Seat seat = seatMapper.toEntity(requestDto);
        seat.setFlight(flight);
        Seat savedSeat = seatRepository.save(seat);
        return seatMapper.toDto(savedSeat);
    }

    @Override
    public List<SeatResponseDto> getSeatsByFlightId(Integer flightId) {
        // Just verify flight exists
        flightRepository.findById(flightId)
                .orElseThrow(() -> new BaseException("error.flight_not_found"));
        List<Seat> seats = seatRepository.findByFlightId(flightId);
        return seatMapper.toDtoList(seats);
    }

    @Override
    public List<SeatResponseDto> getAvailableSeatsByFlightId(Integer flightId) {
        flightRepository.findById(flightId)
                .orElseThrow(() -> new BaseException("error.flight_not_found"));
        List<Seat> availableSeats = seatRepository.findByFlightIdAndIsAvailable(flightId, true);
        return seatMapper.toDtoList(availableSeats);
    }

    @Override
    public SeatResponseDto updateSeat(Integer id, SeatRequestDto requestDto) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new BaseException("error.seat_not_found"));
        
        if (!seat.getFlight().getId().equals(requestDto.getFlightId())) {
            Flight newFlight = flightRepository.findById(requestDto.getFlightId())
                    .orElseThrow(() -> new BaseException("error.flight_not_found"));
            seat.setFlight(newFlight);
        }
        
        seatMapper.updateEntityFromDto(requestDto, seat);
        Seat updatedSeat = seatRepository.save(seat);
        return seatMapper.toDto(updatedSeat);
    }

    @Override
    public void deleteSeat(Integer id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new BaseException("error.seat_not_found"));
        seatRepository.delete(seat);
    }
}

