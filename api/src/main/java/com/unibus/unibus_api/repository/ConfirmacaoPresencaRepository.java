package com.unibus.unibus_api.repository;

import com.unibus.unibus_api.entity.ConfirmacaoPresenca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ConfirmacaoPresencaRepository extends JpaRepository<ConfirmacaoPresenca, Long> {
    List<ConfirmacaoPresenca> findByViagemId(Long viagemId);
    Optional<ConfirmacaoPresenca> findByViagemIdAndPassageiroId(Long viagemId, Long passageiroId);
}