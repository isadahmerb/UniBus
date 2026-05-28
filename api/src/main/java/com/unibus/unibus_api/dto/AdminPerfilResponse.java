package com.unibus.unibus_api.dto;

import lombok.Data;

@Data
public class AdminPerfilResponse {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    public AdminPerfilResponse(com.unibus.unibus_api.entity.Administrador admin) {
        this.id = admin.getId();
        this.nome = admin.getNome();
        this.email = admin.getEmail();
        this.cpf = admin.getCpf();
        this.telefone = admin.getTelefone();
    }
}