package com.unibus.unibus_api.dto;

import com.unibus.unibus_api.entity.Rota;
import lombok.Data;

@Data
public class RotaResponse {
    private Long id;
    private String nome;
    private String descricao;
    private String horarioSaida;
    private String horarioRetorno;
    private boolean ativa;

    public RotaResponse(Rota rota) {
        this.id = rota.getId();
        this.nome = rota.getNome();
        this.descricao = rota.getDescricao();
        this.horarioSaida = rota.getHorarioSaida();
        this.horarioRetorno = rota.getHorarioRetorno();
        this.ativa = rota.isAtiva();
    }
}