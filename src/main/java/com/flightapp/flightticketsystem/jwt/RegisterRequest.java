package com.flightapp.flightticketsystem.jwt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    
    @NotBlank(message = "{email.cannotblank}")
    @Email(message = "{email.validation}")
    private String email;

    @NotBlank(message = "{password.validation}")
    private String password;

    @NotBlank(message = "{firstName.validation}")
    private String firstName;

    @NotBlank(message = "{lastName.validation}")
    private String lastName;
}
