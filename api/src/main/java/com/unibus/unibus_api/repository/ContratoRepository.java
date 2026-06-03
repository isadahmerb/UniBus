package com.unibus.unibus_api.repository;

import com.unibus.unibus_api.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByStatus(Contrato.StatusContrato status);
    List<Contrato> findByPassageiroId(Long passageiroId);
}