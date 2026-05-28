package com.unibus.unibus_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MotoristaRequest {
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String numeroCnh;
    private LocalDate vencimentoCnh;
}