package com.flightapp.flightticketsystem.jwt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    
    @NotBlank(message = "{email.cannotblank}")
    @Email(message = "{email.validation}")
    private String email;

    @NotBlank(message = "{password.validation}")
    private String password;
}
