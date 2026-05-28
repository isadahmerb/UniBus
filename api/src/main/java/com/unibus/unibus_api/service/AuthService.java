package com.unibus.unibus_api.service;

import com.unibus.unibus_api.dto.*;
import com.unibus.unibus_api.entity.Administrador;
import com.unibus.unibus_api.entity.Motorista;
import com.unibus.unibus_api.entity.Passageiro;
import com.unibus.unibus_api.repository.AdministradorRepository;
import com.unibus.unibus_api.repository.MotoristaRepository;
import com.unibus.unibus_api.repository.PassageiroRepository;
import com.unibus.unibus_api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdministradorRepository administradorRepository;
    private final MotoristaRepository motoristaRepository;
    private final PassageiroRepository passageiroRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        Administrador admin = administradorRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoder.matches(request.getSenha(), admin.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(admin.getEmail());
        return new LoginResponse(token, admin.getNome());
    }

    public MotoristaLoginResponse loginMotorista(LoginRequest request) {
        Motorista motorista = motoristaRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoder.matches(request.getSenha(), motorista.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(motorista.getEmail());
        return new MotoristaLoginResponse(token, motorista.getId(), motorista.getNome(), motorista.getEmail());
    }

    public PassageiroLoginResponse loginPassageiro(LoginRequest request) {
        Passageiro passageiro = passageiroRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (!passwordEncoder.matches(request.getSenha(), passageiro.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(passageiro.getEmail());
        return new PassageiroLoginResponse(token, passageiro.getId(), passageiro.getNome(), passageiro.getEmail());
    }
}