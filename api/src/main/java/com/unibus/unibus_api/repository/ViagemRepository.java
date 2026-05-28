package com.unibus.unibus_api.repository;

import com.unibus.unibus_api.entity.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {
    List<Viagem> findByData(LocalDate data);
    List<Viagem> findByMotoristaId(Long motoristaId);
    List<Viagem> findByRotaId(Long rotaId);
}