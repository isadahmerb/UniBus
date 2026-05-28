package com.unibus.unibus_api.repository;

import com.unibus.unibus_api.entity.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {
    Optional<Passageiro> findByEmail(String email);
}