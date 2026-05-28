package com.unibus.unibus_api.service;

import com.unibus.unibus_api.dto.PassageiroRequest;
import com.unibus.unibus_api.dto.PassageiroResponse;
import com.unibus.unibus_api.entity.Passageiro;
import com.unibus.unibus_api.entity.Rota;
import com.unibus.unibus_api.repository.PassageiroRepository;
import com.unibus.unibus_api.repository.RotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassageiroService {

    private final PassageiroRepository repository;
    private final RotaRepository rotaRepository;
    private final PasswordEncoder passwordEncoder;

    public PassageiroResponse criar(PassageiroRequest request) {
        Passageiro passageiro = new Passageiro();
        passageiro.setNome(request.getNome());
        passageiro.setEmail(request.getEmail());
        passageiro.setSenha(passwordEncoder.encode(request.getSenha()));
        passageiro.setCpf(request.getCpf());
        passageiro.setTelefone(request.getTelefone());
        passageiro.setEnderecoPadrao(request.getEnderecoPadrao());

        if (request.getRotaId() != null) {
            Rota rota = rotaRepository.findById(request.getRotaId())
                    .orElseThrow(() -> new RuntimeException("Rota não encontrada"));
            passageiro.setRota(rota);
        }

        repository.save(passageiro);
        return new PassageiroResponse(passageiro);
    }

    public List<PassageiroResponse> listar() {
        return repository.findAll()
                .stream()
                .map(PassageiroResponse::new)
                .collect(Collectors.toList());
    }

    public PassageiroResponse buscarPorId(Long id) {
        Passageiro passageiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));
        return new PassageiroResponse(passageiro);
    }

    public PassageiroResponse atualizar(Long id, PassageiroRequest request) {
        Passageiro passageiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));

        if (request.getNome() != null) passageiro.setNome(request.getNome());
        if (request.getTelefone() != null) passageiro.setTelefone(request.getTelefone());
        if (request.getEnderecoPadrao() != null) passageiro.setEnderecoPadrao(request.getEnderecoPadrao());
        if (request.getSenha() != null) passageiro.setSenha(passwordEncoder.encode(request.getSenha()));
        if (request.getRotaId() != null) {
            Rota rota = rotaRepository.findById(request.getRotaId())
                    .orElseThrow(() -> new RuntimeException("Rota não encontrada"));
            passageiro.setRota(rota);
        }

        repository.save(passageiro);
        return new PassageiroResponse(passageiro);
    }

    public void desativar(Long id) {
        Passageiro passageiro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));
        passageiro.setAtivo(false);
        repository.save(passageiro);
    }
}