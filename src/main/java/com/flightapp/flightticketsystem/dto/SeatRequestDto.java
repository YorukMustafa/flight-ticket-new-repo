package com.flightapp.flightticketsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatRequestDto {

    @NotBlank(message = "{error.seat_number_blank}")
    private String seatNumber;

    @NotBlank(message = "{error.seat_type_blank}")
    private String seatType;

    private boolean isAvailable;

    @NotNull(message = "{error.price_null}")
    private BigDecimal price;

    @NotNull(message = "{error.flight_id_null}")
    private Integer flightId;
}
