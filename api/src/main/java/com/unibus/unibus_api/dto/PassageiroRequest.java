package com.unibus.unibus_api.dto;

import lombok.Data;

@Data
public class PassageiroRequest {
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String enderecoPadrao;
    private Long rotaId;
}