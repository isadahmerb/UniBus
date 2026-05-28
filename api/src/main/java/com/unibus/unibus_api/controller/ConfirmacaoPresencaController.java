package com.unibus.unibus_api.controller;

import com.unibus.unibus_api.dto.ConfirmacaoPresencaRequest;
import com.unibus.unibus_api.dto.ConfirmacaoPresencaResponse;
import com.unibus.unibus_api.service.ConfirmacaoPresencaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagens")
@RequiredArgsConstructor
public class ConfirmacaoPresencaController {

    private final ConfirmacaoPresencaService confirmacaoService;

    @PostMapping("/{viagemId}/confirmacoes")
    public ResponseEntity<ConfirmacaoPresencaResponse> confirmar(@PathVariable Long viagemId,
                                                                 @RequestBody ConfirmacaoPresencaRequest request) {
        return ResponseEntity.status(201).body(confirmacaoService.confirmar(viagemId, request));
    }

    @DeleteMapping("/{viagemId}/confirmacoes/{passageiroId}")
    public ResponseEntity<ConfirmacaoPresencaResponse> cancelar(@PathVariable Long viagemId,
                                                                @PathVariable Long passageiroId) {
        return ResponseEntity.ok(confirmacaoService.cancelar(viagemId, passageiroId));
    }

    @GetMapping("/{viagemId}/confirmacoes")
    public ResponseEntity<List<ConfirmacaoPresencaResponse>> listar(@PathVariable Long viagemId) {
        return ResponseEntity.ok(confirmacaoService.listarPorViagem(viagemId));
    }
}