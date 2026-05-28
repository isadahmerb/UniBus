package com.unibus.unibus_api.dto;

import lombok.Data;

@Data
public class ConfirmacaoPresencaRequest {
    private Long passageiroId;
    private String enderecoDoDia;
}