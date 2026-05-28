package com.unibus.unibus_api.service;

import com.unibus.unibus_api.dto.RotaRequest;
import com.unibus.unibus_api.dto.RotaResponse;
import com.unibus.unibus_api.entity.Rota;
import com.unibus.unibus_api.repository.RotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository repository;

    public RotaResponse criar(RotaRequest request) {
        Rota rota = new Rota();
        rota.setNome(request.getNome());
        rota.setDescricao(request.getDescricao());
        rota.setHorarioSaida(request.getHorarioSaida());
        rota.setHorarioRetorno(request.getHorarioRetorno());
        repository.save(rota);
        return new RotaResponse(rota);
    }

    public List<RotaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(RotaResponse::new)
                .collect(Collectors.toList());
    }

    public RotaResponse buscarPorId(Long id) {
        Rota rota = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rota não encontrada"));
        return new RotaResponse(rota);
    }

    public RotaResponse atualizar(Long id, RotaRequest request) {
        Rota rota = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rota não encontrada"));

        if (request.getNome() != null) rota.setNome(request.getNome());
        if (request.getDescricao() != null) rota.setDescricao(request.getDescricao());
        if (request.getHorarioSaida() != null) rota.setHorarioSaida(request.getHorarioSaida());
        if (request.getHorarioRetorno() != null) rota.setHorarioRetorno(request.getHorarioRetorno());

        repository.save(rota);
        return new RotaResponse(rota);
    }

    public void desativar(Long id) {
        Rota rota = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rota não encontrada"));
        rota.setAtiva(false);
        repository.save(rota);
    }
}