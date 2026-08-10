package com.flightapp.flightticketsystem.dto;

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
    private String seatNumber;
    private String seatType;
    private boolean isAvailable;
    private BigDecimal price;
    private Integer flightId;
}
