package com.unibus.unibus_api.service;

import com.unibus.unibus_api.dto.ContratoRequest;
import com.unibus.unibus_api.dto.ContratoResponse;
import com.unibus.unibus_api.entity.Contrato;
import com.unibus.unibus_api.entity.Passageiro;
import com.unibus.unibus_api.entity.Rota;
import com.unibus.unibus_api.repository.ContratoRepository;
import com.unibus.unibus_api.repository.PassageiroRepository;
import com.unibus.unibus_api.repository.RotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final PassageiroRepository passageiroRepository;
    private final RotaRepository rotaRepository;

    public ContratoResponse criar(ContratoRequest request) {
        Passageiro passageiro = passageiroRepository.findById(request.getPassageiroId())
                .orElseThrow(() -> new RuntimeException("Passageiro não encontrado"));
        Rota rota = rotaRepository.findById(request.getRotaId())
                .orElseThrow(() -> new RuntimeException("Rota não encontrada"));

        Contrato contrato = new Contrato();
        contrato.setPassageiro(passageiro);
        contrato.setRota(rota);
        contrato.setDataInicio(request.getDataInicio());
        contrato.setDataFim(request.getDataFim());
        contrato.setStatus(calcularStatus(request.getDataFim()));

        contratoRepository.save(contrato);
        return new ContratoResponse(contrato);
    }

    public List<ContratoResponse> listar() {
        return contratoRepository.findAll()
                .stream()
                .map(c -> {
                    c.setStatus(calcularStatus(c.getDataFim()));
                    contratoRepository.save(c);
                    return new ContratoResponse(c);
                })
                .collect(Collectors.toList());
    }

    public List<ContratoResponse> listarPorStatus(String status) {
        return contratoRepository.findByStatus(Contrato.StatusContrato.valueOf(status))
                .stream()
                .map(ContratoResponse::new)
                .collect(Collectors.toList());
    }

    public ContratoResponse renovar(Long id, LocalDate novaDataFim) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
        contrato.setDataFim(novaDataFim);
        contrato.setStatus(calcularStatus(novaDataFim));
        contratoRepository.save(contrato);
        return new ContratoResponse(contrato);
    }

    private Contrato.StatusContrato calcularStatus(LocalDate dataFim) {
        LocalDate hoje = LocalDate.now();
        if (dataFim.isBefore(hoje)) return Contrato.StatusContrato.EXPIRADO;
        if (dataFim.isBefore(hoje.plusDays(30))) return Contrato.StatusContrato.A_EXPIRAR;
        return Contrato.StatusContrato.VIGENTE;
    }
}