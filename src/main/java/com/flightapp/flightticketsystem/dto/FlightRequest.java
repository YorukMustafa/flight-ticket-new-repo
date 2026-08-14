package com.flightapp.flightticketsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightRequest {

    @NotBlank(message = "{error.flight_no_blank}")
    private String flightNo;

    @NotBlank(message = "{error.departure_blank}")
    private String departurePoint;

    @NotBlank(message = "{error.destination_blank}")
    private String destinationPoint;

    @NotBlank(message = "{error.time_null}")
    private String departureTime;

    @NotBlank(message = "{error.time_null}")
    private String destinationTime;

}
