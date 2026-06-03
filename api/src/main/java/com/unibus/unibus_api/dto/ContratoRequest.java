package com.unibus.unibus_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ContratoRequest {
    private Long passageiroId;
    private Long rotaId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}