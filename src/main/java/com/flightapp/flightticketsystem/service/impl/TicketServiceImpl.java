package com.flightapp.flightticketsystem.service.impl;

import com.flightapp.flightticketsystem.entities.Seat;
import com.flightapp.flightticketsystem.exception.BaseException;
import com.flightapp.flightticketsystem.repository.SeatRepository;
import com.flightapp.flightticketsystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final SeatRepository seatRepository;

    @Override
    @Transactional
    public void buyTicket(Integer seatId) {

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new BaseException("Koltuk bulunamadı!"));


        if (!seat.isAvailable()) {
            throw new BaseException("error.seat_already_sold");
        }

        // Iyzico kodları...
        // seat.setAvailable(false);
        // seatRepository.save(seat);
    }
}
