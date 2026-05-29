package com.unibus.unibus_api.controller;

import com.unibus.unibus_api.dto.ConfirmacaoPresencaResponse;
import com.unibus.unibus_api.dto.ViagemRequest;
import com.unibus.unibus_api.dto.ViagemResponse;
import com.unibus.unibus_api.service.ViagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagens")
@RequiredArgsConstructor
public class ViagemController {

    private final ViagemService viagemService;

    @PostMapping
    public ResponseEntity<ViagemResponse> criar(@RequestBody ViagemRequest request) {
        return ResponseEntity.status(201).body(viagemService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<ViagemResponse>> listar() {
        return ResponseEntity.ok(viagemService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViagemResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(viagemService.buscarPorId(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ViagemResponse> atualizarStatus(@PathVariable Long id,
                                                          @RequestParam String status) {
        return ResponseEntity.ok(viagemService.atualizarStatus(id, status));
    }

    @GetMapping("/{id}/roteiro")
    public ResponseEntity<List<ConfirmacaoPresencaResponse>> getRoteiro(@PathVariable Long id) {
        return ResponseEntity.ok(viagemService.getRoteiroDodia(id));
    }
}