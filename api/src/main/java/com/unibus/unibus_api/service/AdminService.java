package com.unibus.unibus_api.service;

import com.unibus.unibus_api.dto.AdminPerfilResponse;
import com.unibus.unibus_api.dto.AdminUpdateRequest;
import com.unibus.unibus_api.entity.Administrador;
import com.unibus.unibus_api.repository.AdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdministradorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AdminPerfilResponse getPerfil(String email) {
        Administrador admin = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
        return new AdminPerfilResponse(admin);
    }

    public AdminPerfilResponse updatePerfil(String email, AdminUpdateRequest request) {
        Administrador admin = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        if (request.getNome() != null) {
            admin.setNome(request.getNome());
        }

        if (request.getTelefone() != null) {
            admin.setTelefone(request.getTelefone());
        }

        if (request.getNovaSenha() != null) {
            if (!passwordEncoder.matches(request.getSenhaAtual(), admin.getSenha())) {
                throw new RuntimeException("Senha atual incorreta");
            }
            admin.setSenha(passwordEncoder.encode(request.getNovaSenha()));
        }

        repository.save(admin);
        return new AdminPerfilResponse(admin);
    }
}