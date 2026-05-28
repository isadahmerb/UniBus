package com.unibus.unibus_api.dto;

import com.unibus.unibus_api.entity.ConfirmacaoPresenca;
import lombok.Data;

@Data
public class ConfirmacaoPresencaResponse {
    private Long id;
    private Long viagemId;
    private Long passageiroId;
    private String passageiroNome;
    private String status;
    private String enderecoDoDia;

    public ConfirmacaoPresencaResponse(ConfirmacaoPresenca confirmacao) {
        this.id = confirmacao.getId();
        this.viagemId = confirmacao.getViagem().getId();
        this.passageiroId = confirmacao.getPassageiro().getId();
        this.passageiroNome = confirmacao.getPassageiro().getNome();
        this.status = confirmacao.getStatus().name();
        this.enderecoDoDia = confirmacao.getEnderecoDoDia();
    }
}