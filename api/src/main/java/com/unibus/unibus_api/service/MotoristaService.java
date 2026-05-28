package com.unibus.unibus_api.service;

import com.unibus.unibus_api.dto.MotoristaRequest;
import com.unibus.unibus_api.dto.MotoristaResponse;
import com.unibus.unibus_api.entity.Motorista;
import com.unibus.unibus_api.repository.MotoristaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MotoristaService {

    private final MotoristaRepository repository;
    private final PasswordEncoder passwordEncoder;

    public MotoristaResponse criar(MotoristaRequest request) {
        Motorista motorista = new Motorista();
        motorista.setNome(request.getNome());
        motorista.setEmail(request.getEmail());
        motorista.setSenha(passwordEncoder.encode(request.getSenha()));
        motorista.setCpf(request.getCpf());
        motorista.setTelefone(request.getTelefone());
        motorista.setNumeroCnh(request.getNumeroCnh());
        motorista.setVencimentoCnh(request.getVencimentoCnh());
        repository.save(motorista);
        return new MotoristaResponse(motorista);
    }

    public List<MotoristaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(MotoristaResponse::new)
                .collect(Collectors.toList());
    }

    public MotoristaResponse buscarPorId(Long id) {
        Motorista motorista = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));
        return new MotoristaResponse(motorista);
    }

    public MotoristaResponse atualizar(Long id, MotoristaRequest request) {
        Motorista motorista = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));

        if (request.getNome() != null) motorista.setNome(request.getNome());
        if (request.getTelefone() != null) motorista.setTelefone(request.getTelefone());
        if (request.getNumeroCnh() != null) motorista.setNumeroCnh(request.getNumeroCnh());
        if (request.getVencimentoCnh() != null) motorista.setVencimentoCnh(request.getVencimentoCnh());
        if (request.getSenha() != null) motorista.setSenha(passwordEncoder.encode(request.getSenha()));

        repository.save(motorista);
        return new MotoristaResponse(motorista);
    }

    public void desativar(Long id) {
        Motorista motorista = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));
        motorista.setAtivo(false);
        repository.save(motorista);
    }
}