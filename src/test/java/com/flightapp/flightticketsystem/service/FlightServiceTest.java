package com.flightapp.flightticketsystem.service;

import com.flightapp.flightticketsystem.dto.FlightRequest;
import com.flightapp.flightticketsystem.dto.FlightResponse;
import com.flightapp.flightticketsystem.entities.Flight;
import com.flightapp.flightticketsystem.exception.BaseException;
import com.flightapp.flightticketsystem.mapper.FlightMapper;
import com.flightapp.flightticketsystem.repository.FlightRepository;
import com.flightapp.flightticketsystem.service.impl.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //mokito kütükhanesini dahil eder
public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightMapper flightMapper;

    @InjectMocks
    private FlightService flightService;


    @Test
    public void addFlightTest(){

        FlightRequest request = new FlightRequest();
        request.setFlightNo("TK12345");

        Flight flight = new Flight();
        flight.setFlightNo("TK12345");

        FlightResponse response = new FlightResponse();
        response.setFlightNo("TK12345");

        when(flightMapper.toEntity(request)).thenReturn(flight);
        when(flightRepository.save(flight)).thenReturn(flight);
        when(flightMapper.toResponse(flight)).thenReturn(response);

        FlightResponse result = flightService.addFlight(request);

        assertNotNull(result);
        assertEquals("TK12345", result.getFlightNo());

        verify(flightRepository, times(1)).save(flight);
    }


    @Test
    public void getFlightByIdTest(){

        Flight flight = new Flight();
        flight.setFlightNo("TK12345");
        flight.setId(1);

        FlightResponse response = new FlightResponse();
        response.setFlightNo("TK12345");

        when(flightRepository.findById(1)).thenReturn(Optional.of(flight));
        when(flightMapper.toResponse(flight)).thenReturn(response);

        FlightResponse result = flightService.getFlightById(1);

        assertNotNull(result);
        assertEquals("TK12345", result.getFlightNo());
        verify(flightRepository, times(1)).findById(1);

    }

    @Test
    public void getFlightById_WhenNotFound_ShouldReturnBaseException() {


        when(flightRepository.findById(90)).thenReturn(Optional.empty());

        assertThrows(BaseException.class, () -> flightService.getFlightById(90));

        verify(flightRepository, times(1)).findById(90);
        verifyNoInteractions(flightMapper); //kodun o satıra hiç inmediğini söyler
    }


    @Test
    public void getAllFlightsTest(){

        Flight flight1 = new Flight();
        flight1.setId(1);

        Flight flight2 = new Flight();
        flight2.setId(2);

        List<Flight> flightList = List.of(flight1, flight2);

        FlightResponse response1 = new FlightResponse();
        response1.setFlightNo("TK12345");

        FlightResponse response2 = new FlightResponse();
        response2.setFlightNo("TK12345");

        when(flightRepository.findAll()).thenReturn(flightList);
        when(flightMapper.toResponse(flight1)).thenReturn(response1);
        when(flightMapper.toResponse(flight2)).thenReturn(response2);

        List<FlightResponse> result = flightService.getAllFlights();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(flightRepository, times(1)).findAll();

    }


    @Test
    public void updateFlightTest(){

        FlightRequest request = new FlightRequest();
        request.setFlightNo("TK12345");
        request.setDeparturePoint("İstanbul");
        request.setDestinationPoint("İzmir");
        request.setDepartureTime("2026-10-15 14:30");
        request.setDestinationTime("2026-10-15 16:30");

        Flight flight = new Flight();
        flight.setFlightNo("TK12345");

        FlightResponse responseMock = new FlightResponse();
        responseMock.setFlightNo("TK12345");

        when(flightRepository.findById(1)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        when(flightMapper.toResponse(any(Flight.class))).thenReturn(responseMock);

        FlightResponse response = flightService.updateFlight(1, request);

        assertNotNull(response);
        assertEquals("TK12345", response.getFlightNo());

        verify(flightRepository, times(1)).findById(1);
        verify(flightRepository, times(1)).save(any(Flight.class));
        verify(flightMapper, times(1)).toResponse(any(Flight.class));
    }

    @Test
    public void updateFlight_WhenNotFound_ShouldReturnBaseException() {

        FlightRequest request = new FlightRequest();
        request.setFlightNo("TK12345");
        request.setDeparturePoint("Çanakkale");
        request.setDestinationPoint("Muğla");
        request.setDepartureTime("2026-10-15 14:30");
        request.setDestinationTime("2026-10-15 15:30");

        when(flightRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(BaseException.class, () -> flightService.updateFlight(99,request));

        verify(flightRepository, never()).save(any());
    }

    @Test
    public void deleteFlightTest(){

        Flight flight = new Flight();
        flight.setFlightNo("TK12345");
        flight.setId(1);

        when(flightRepository.findById(1)).thenReturn(Optional.of(flight));

        flightService.deleteFlight(1);
        verify(flightRepository, times(1)).findById(1);
        verify(flightRepository, times(1)).delete(flight);
    }

    @Test
    public void deleteFlight_WhenNotFound_ShouldReturnBaseException() {

        when(flightRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(BaseException.class, () -> flightService.deleteFlight(99));

        verify(flightRepository, never()).delete(any());
    }
}
