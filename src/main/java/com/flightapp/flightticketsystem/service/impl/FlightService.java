package com.flightapp.flightticketsystem.service.impl;

import com.flightapp.flightticketsystem.dto.FlightRequest;
import com.flightapp.flightticketsystem.dto.FlightResponse;
import com.flightapp.flightticketsystem.entities.Flight;
import com.flightapp.flightticketsystem.exception.BaseException;
import com.flightapp.flightticketsystem.mapper.FlightMapper;
import com.flightapp.flightticketsystem.repository.FlightRepository;
import com.flightapp.flightticketsystem.service.IFlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService implements IFlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;


    @Override
    public FlightResponse addFlight(FlightRequest flightRequest) {

        Flight flight = flightMapper.toEntity(flightRequest);
        flight = flightRepository.save(flight);
        FlightResponse flightResponse = flightMapper.toResponse(flight);
        return flightResponse;
    }

    @Override
    public FlightResponse getFlightById(Integer id) {

        Flight flight = flightRepository.findById(id).orElseThrow(() -> new BaseException("error.flight_not_found"));

        return flightMapper.toResponse(flight);
    }

    @Override
    public List<FlightResponse> getAllFlights() {

        List<Flight> flights = flightRepository.findAll();

        List<FlightResponse> flightResponseList = new ArrayList<>();

        for (Flight flight : flights) {
            FlightResponse flightResponse = flightMapper.toResponse(flight);
            flightResponseList.add(flightResponse);
        }
        return flightResponseList;
    }

    @Override
    public FlightResponse updateFlight(Integer id, FlightRequest flightRequest) {

        Flight flight = flightRepository.findById(id).orElseThrow(() -> new BaseException("error.flight_not_found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        flight.setFlightNo(flightRequest.getFlightNo());
        flight.setDeparturePoint(flightRequest.getDeparturePoint());
        flight.setDestinationPoint(flightRequest.getDestinationPoint());

        if (flightRequest.getDepartureTime() != null) {
            flight.setDepartureTime(LocalDateTime.parse(flightRequest.getDepartureTime(), formatter));
        }

        if (flightRequest.getDestinationTime() != null) {
            flight.setDestinationTime(LocalDateTime.parse(flightRequest.getDestinationTime(), formatter));
        }

        flight = flightRepository.save(flight);
        return flightMapper.toResponse(flight);
    }

    @Override
    public void deleteFlight(Integer id) {

        Flight flight = flightRepository.findById(id).orElseThrow(() -> new BaseException("error.flight_not_found"));
        flightRepository.delete(flight);
    }
}
