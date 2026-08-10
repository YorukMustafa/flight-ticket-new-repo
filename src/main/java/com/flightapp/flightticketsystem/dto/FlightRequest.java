package com.flightapp.flightticketsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "{error.time_null}")
    private String departureTime;

    @NotNull(message = "{error.time_null}")
    private String destinationTime;
}
