package com.unibus.unibus_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ViagemRequest {
    private Long rotaId;
    private Long motoristaId;
    private LocalDate data;
}