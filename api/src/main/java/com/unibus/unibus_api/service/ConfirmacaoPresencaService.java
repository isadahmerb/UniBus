package com.unibus.unibus_api.service;

import com.unibus.unibus_api.dto.ConfirmacaoPresencaRequest;
import com.unibus.unibus_api.dto.ConfirmacaoPresencaResponse;
import com.unibus.unibus_api.entity.*;
import com.unibus.unibus_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConfirmacaoPresencaService {

    private final ConfirmacaoPresencaRepository confirmacaoRepository;
    private final ViagemRepository viagemRepository;
    private final PassageiroRepository passageiroRepository;

    public ConfirmacaoPresencaResponse confirmar(Long viagemId, ConfirmacaoPresencaRequest request) {
        Viagem viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
        Passageiro passageiro = passageiroRepository.findById(request.getPassageiroId())
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));

        ConfirmacaoPresenca confirmacao = confirmacaoRepository
                .findByViagemIdAndPassageiroId(viagemId, request.getPassageiroId())
                .orElse(new ConfirmacaoPresenca());

        confirmacao.setViagem(viagem);
        confirmacao.setPassageiro(passageiro);
        confirmacao.setStatus(ConfirmacaoPresenca.StatusPresenca.CONFIRMADO);

        if (request.getEnderecoDoDia() != null) {
            confirmacao.setEnderecoDoDia(request.getEnderecoDoDia());
        } else {
            confirmacao.setEnderecoDoDia(passageiro.getEnderecoPadrao());
        }

        confirmacaoRepository.save(confirmacao);
        return new ConfirmacaoPresencaResponse(confirmacao);
    }

    public ConfirmacaoPresencaResponse cancelar(Long viagemId, Long passageiroId) {
        ConfirmacaoPresenca confirmacao = confirmacaoRepository
                .findByViagemIdAndPassageiroId(viagemId, passageiroId)
                .orElseThrow(() -> new RuntimeException("Confirmação não encontrada"));
        confirmacao.setStatus(ConfirmacaoPresenca.StatusPresenca.CANCELADO);
        confirmacaoRepository.save(confirmacao);
        return new ConfirmacaoPresencaResponse(confirmacao);
    }

    public List<ConfirmacaoPresencaResponse> listarPorViagem(Long viagemId) {
        return confirmacaoRepository.findByViagemId(viagemId)
                .stream()
                .map(ConfirmacaoPresencaResponse::new)
                .collect(Collectors.toList());
    }
}