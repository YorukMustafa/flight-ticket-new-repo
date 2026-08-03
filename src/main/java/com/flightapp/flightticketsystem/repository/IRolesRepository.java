package com.flightapp.flightticketsystem.repository;

import com.flightapp.flightticketsystem.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);
}
