package com.unibus.unibus_api.dto;

import com.unibus.unibus_api.entity.Motorista;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MotoristaResponse {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String numeroCnh;
    private LocalDate vencimentoCnh;
    private boolean ativo;

    public MotoristaResponse(Motorista motorista) {
        this.id = motorista.getId();
        this.nome = motorista.getNome();
        this.email = motorista.getEmail();
        this.cpf = motorista.getCpf();
        this.telefone = motorista.getTelefone();
        this.numeroCnh = motorista.getNumeroCnh();
        this.vencimentoCnh = motorista.getVencimentoCnh();
        this.ativo = motorista.isAtivo();
    }
}