package com.flightapp.flightticketsystem.controller;

import com.flightapp.flightticketsystem.dto.FlightRequest;
import com.flightapp.flightticketsystem.dto.FlightResponse;
import com.flightapp.flightticketsystem.service.IFlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api/flight")
@RequiredArgsConstructor
public class FlightController {

    private final IFlightService flightService;


    @PreAuthorize("hasRole('ADMIN')") //Bu metoda sadece yetkilerinde ROLE_ADMIN olan kişiler girebilir diyoruz
    @PostMapping(path = "/add-flight")
    public FlightResponse addFlight(@Valid @RequestBody FlightRequest flightRequest) {
        return flightService.addFlight(flightRequest);
    }

    @GetMapping(path = "/list-all-flights")
    public List<FlightResponse> listAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping(path = "/flights-{id}")
    public FlightResponse getFlightById(@PathVariable(name = "id") Integer id){
        return flightService.getFlightById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/save-flight-{id}")
    public FlightResponse saveFlight(@PathVariable(name = "id") Integer id , @Valid @RequestBody FlightRequest flightRequest){
        return flightService.updateFlight(id, flightRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete-flight-{id}")
    public void deleteFlight(@PathVariable(name = "id") Integer id){
        flightService.deleteFlight(id);
    }

}
