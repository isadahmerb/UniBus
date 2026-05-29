package com.unibus.unibus_api.repository;

import com.unibus.unibus_api.entity.LocalizacaoMotorista;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LocalizacaoMotoristaRepository extends JpaRepository<LocalizacaoMotorista, Long> {
    Optional<LocalizacaoMotorista> findTopByViagemIdOrderByAtualizadoEmDesc(Long viagemId);
}