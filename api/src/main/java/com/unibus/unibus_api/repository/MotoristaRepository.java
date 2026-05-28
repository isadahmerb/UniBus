package com.unibus.unibus_api.repository;

import com.unibus.unibus_api.entity.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    Optional<Motorista> findByEmail(String email);
}