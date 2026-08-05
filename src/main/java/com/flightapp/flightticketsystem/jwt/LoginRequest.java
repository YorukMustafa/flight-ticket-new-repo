package com.flightapp.flightticketsystem.jwt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    
    @NotBlank(message = "{error.validation}")
    @Email(message = "{error.validation}")
    private String email;

    @NotBlank(message = "{error.validation}")
    private String password;
}
