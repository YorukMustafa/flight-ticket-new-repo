package com.flightapp.flightticketsystem.jwt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    
    @NotBlank(message = "{error.validation}")
    @Email(message = "{error.validation}")
    private String email;

    @NotBlank(message = "{error.validation}")
    private String password;

    @NotBlank(message = "{error.validation}")
    private String firstName;
    
    @NotBlank(message = "{error.validation}")
    private String lastName;
}
