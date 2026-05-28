package com.unibus.unibus_api.service;

import com.unibus.unibus_api.dto.LoginRequest;
import com.unibus.unibus_api.dto.LoginResponse;
import com.unibus.unibus_api.entity.Administrador;
import com.unibus.unibus_api.repository.AdministradorRepository;
import com.unibus.unibus_api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdministradorRepository repository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        Administrador admin = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoder.matches(request.getSenha(), admin.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(admin.getEmail());
        return new LoginResponse(token, admin.getNome());
    }
}