package com.flightapp.flightticketsystem.service.impl;

import com.flightapp.flightticketsystem.entities.User;
import com.flightapp.flightticketsystem.exception.BaseException;
import com.flightapp.flightticketsystem.jwt.LoginRequest;
import com.flightapp.flightticketsystem.jwt.RegisterRequest;
import com.flightapp.flightticketsystem.jwt.AuthResponse;
import com.flightapp.flightticketsystem.jwt.JwtService;

import com.flightapp.flightticketsystem.repository.RoleRepository;
import com.flightapp.flightticketsystem.repository.UserRepository;
import com.flightapp.flightticketsystem.service.IAuthService;
import com.flightapp.flightticketsystem.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository usersRepository;
    private final RoleRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (usersRepository.existsByEmail(request.getEmail())) {
            throw new BaseException("error.user_already_exists");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        rolesRepository.findByRoleName("Customer").ifPresent(role -> user.getRoles().add(role));

        usersRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BaseException("error.user_not_found"));

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }
}
