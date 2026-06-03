package com.unibus.unibus_api.dto;

import com.unibus.unibus_api.entity.Contrato;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ContratoResponse {
    private Long id;
    private Long passageiroId;
    private String passageiroNome;
    private Long rotaId;
    private String rotaNome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;

    public ContratoResponse(Contrato contrato) {
        this.id = contrato.getId();
        this.passageiroId = contrato.getPassageiro().getId();
        this.passageiroNome = contrato.getPassageiro().getNome();
        this.rotaId = contrato.getRota().getId();
        this.rotaNome = contrato.getRota().getNome();
        this.dataInicio = contrato.getDataInicio();
        this.dataFim = contrato.getDataFim();
        this.status = contrato.getStatus().name();
    }
}