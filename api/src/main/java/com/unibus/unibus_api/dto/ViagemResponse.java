package com.unibus.unibus_api.dto;

import com.unibus.unibus_api.entity.Viagem;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ViagemResponse {
    private Long id;
    private Long rotaId;
    private String rotaNome;
    private Long motoristaId;
    private String motoristaNome;
    private LocalDate data;
    private String status;

    public ViagemResponse(Viagem viagem) {
        this.id = viagem.getId();
        this.rotaId = viagem.getRota().getId();
        this.rotaNome = viagem.getRota().getNome();
        this.motoristaId = viagem.getMotorista().getId();
        this.motoristaNome = viagem.getMotorista().getNome();
        this.data = viagem.getData();
        this.status = viagem.getStatus().name();
    }
}