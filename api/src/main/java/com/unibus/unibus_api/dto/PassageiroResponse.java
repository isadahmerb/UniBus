package com.unibus.unibus_api.dto;

import com.unibus.unibus_api.entity.Passageiro;
import lombok.Data;

@Data
public class PassageiroResponse {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String enderecoPadrao;
    private Long rotaId;
    private String rotaNome;
    private boolean ativo;

    public PassageiroResponse(Passageiro passageiro) {
        this.id = passageiro.getId();
        this.nome = passageiro.getNome();
        this.email = passageiro.getEmail();
        this.cpf = passageiro.getCpf();
        this.telefone = passageiro.getTelefone();
        this.enderecoPadrao = passageiro.getEnderecoPadrao();
        this.ativo = passageiro.isAtivo();
        if (passageiro.getRota() != null) {
            this.rotaId = passageiro.getRota().getId();
            this.rotaNome = passageiro.getRota().getNome();
        }
    }
}