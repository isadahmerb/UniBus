package com.unibus.unibus_api.dto;

import lombok.Data;

@Data
public class RotaRequest {
    private String nome;
    private String descricao;
    private String horarioSaida;
    private String horarioRetorno;
}