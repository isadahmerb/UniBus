package com.unibus.unibus_api.service;

import com.unibus.unibus_api.dto.LocalizacaoRequest;
import com.unibus.unibus_api.dto.LocalizacaoResponse;
import com.unibus.unibus_api.entity.LocalizacaoMotorista;
import com.unibus.unibus_api.entity.Viagem;
import com.unibus.unibus_api.repository.LocalizacaoMotoristaRepository;
import com.unibus.unibus_api.repository.ViagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LocalizacaoService {

    private final LocalizacaoMotoristaRepository localizacaoRepository;
    private final ViagemRepository viagemRepository;

    public LocalizacaoResponse atualizar(Long viagemId, LocalizacaoRequest request) {
        Viagem viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        LocalizacaoMotorista loc = new LocalizacaoMotorista();
        loc.setViagem(viagem);
        loc.setLatitude(request.getLatitude());
        loc.setLongitude(request.getLongitude());
        loc.setAtualizadoEm(LocalDateTime.now());

        localizacaoRepository.save(loc);
        return new LocalizacaoResponse(loc);
    }

    public LocalizacaoResponse obterUltima(Long viagemId) {
        return localizacaoRepository
                .findTopByViagemIdOrderByAtualizadoEmDesc(viagemId)
                .map(LocalizacaoResponse::new)
                .orElseThrow(() -> new RuntimeException("Nenhuma localização disponível"));
    }
}