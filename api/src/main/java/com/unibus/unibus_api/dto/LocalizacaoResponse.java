package com.unibus.unibus_api.dto;

import com.unibus.unibus_api.entity.LocalizacaoMotorista;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LocalizacaoResponse {
    private Double latitude;
    private Double longitude;
    private LocalDateTime atualizadoEm;

    public LocalizacaoResponse(LocalizacaoMotorista loc) {
        this.latitude = loc.getLatitude();
        this.longitude = loc.getLongitude();
        this.atualizadoEm = loc.getAtualizadoEm();
    }
}