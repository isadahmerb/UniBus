package com.unibus.unibus_api.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String nome;

    public LoginResponse(String token, String nome) {
        this.token = token;
        this.nome = nome;
    }
}