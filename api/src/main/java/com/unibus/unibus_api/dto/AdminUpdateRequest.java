package com.unibus.unibus_api.dto;

import lombok.Data;

@Data
public class AdminUpdateRequest {
    private String nome;
    private String telefone;
    private String senhaAtual;
    private String novaSenha;
}