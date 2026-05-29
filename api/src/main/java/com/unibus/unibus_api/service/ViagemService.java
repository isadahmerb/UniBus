package com.unibus.unibus_api.service;

import com.unibus.unibus_api.dto.ConfirmacaoPresencaResponse;
import com.unibus.unibus_api.repository.ConfirmacaoPresencaRepository;
import com.unibus.unibus_api.dto.ViagemRequest;
import com.unibus.unibus_api.dto.ViagemResponse;
import com.unibus.unibus_api.entity.*;
import com.unibus.unibus_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViagemService {
    private final ConfirmacaoPresencaRepository confirmacaoRepository;
    private final ViagemRepository viagemRepository;
    private final RotaRepository rotaRepository;
    private final MotoristaRepository motoristaRepository;

    public ViagemResponse criar(ViagemRequest request) {
        Rota rota = rotaRepository.findById(request.getRotaId())
                .orElseThrow(() -> new RuntimeException("Rota não encontrada"));
        Motorista motorista = motoristaRepository.findById(request.getMotoristaId())
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));

        Viagem viagem = new Viagem();
        viagem.setRota(rota);
        viagem.setMotorista(motorista);
        viagem.setData(request.getData());

        viagemRepository.save(viagem);
        return new ViagemResponse(viagem);
    }

    public List<ViagemResponse> listar() {
        return viagemRepository.findAll()
                .stream()
                .map(ViagemResponse::new)
                .collect(Collectors.toList());
    }

    public ViagemResponse buscarPorId(Long id) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
        return new ViagemResponse(viagem);
    }

    public ViagemResponse atualizarStatus(Long id, String status) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
        viagem.setStatus(Viagem.StatusViagem.valueOf(status));
        viagemRepository.save(viagem);
        return new ViagemResponse(viagem);
    }

    public List<ConfirmacaoPresencaResponse> getRoteiroDodia(Long viagemId) {
        viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        return confirmacaoRepository.findByViagemId(viagemId)
                .stream()
                .filter(c -> c.getStatus() == ConfirmacaoPresenca.StatusPresenca.CONFIRMADO)
                .map(ConfirmacaoPresencaResponse::new)
                .collect(Collectors.toList());
    }
}