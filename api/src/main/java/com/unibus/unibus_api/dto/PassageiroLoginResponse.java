package com.unibus.unibus_api.dto;

import lombok.Data;

@Data
public class PassageiroLoginResponse {
    private String token;
    private Long id;
    private String nome;
    private String email;

    public PassageiroLoginResponse(String token, Long id, String nome, String email) {
        this.token = token;
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
}