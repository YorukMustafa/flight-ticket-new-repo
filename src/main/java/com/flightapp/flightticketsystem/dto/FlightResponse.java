package com.flightapp.flightticketsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {

    private Integer id;
    private String flightNo;
    private String departurePoint;
    private String destinationPoint;
    private String departureTime;
    private String destinationTime;

}
