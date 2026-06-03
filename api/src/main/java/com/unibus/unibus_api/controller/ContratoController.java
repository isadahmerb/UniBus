package com.unibus.unibus_api.controller;

import com.unibus.unibus_api.dto.ContratoRequest;
import com.unibus.unibus_api.dto.ContratoResponse;
import com.unibus.unibus_api.service.ContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/contratos")
@RequiredArgsConstructor
public class ContratoController {

    private final ContratoService contratoService;

    @PostMapping
    public ResponseEntity<ContratoResponse> criar(@RequestBody ContratoRequest request) {
        return ResponseEntity.status(201).body(contratoService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<ContratoResponse>> listar() {
        return ResponseEntity.ok(contratoService.listar());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ContratoResponse>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(contratoService.listarPorStatus(status));
    }

    @PatchMapping("/{id}/renovar")
    public ResponseEntity<ContratoResponse> renovar(@PathVariable Long id,
                                                    @RequestParam String novaDataFim) {
        return ResponseEntity.ok(contratoService.renovar(id, LocalDate.parse(novaDataFim)));
    }
}