package com.unibus.unibus_api.controller;

import com.unibus.unibus_api.dto.RotaRequest;
import com.unibus.unibus_api.dto.RotaResponse;
import com.unibus.unibus_api.service.RotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotas")
@RequiredArgsConstructor
public class RotaController {

    private final RotaService rotaService;

    @PostMapping
    public ResponseEntity<RotaResponse> criar(@RequestBody RotaRequest request) {
        return ResponseEntity.status(201).body(rotaService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<RotaResponse>> listar() {
        return ResponseEntity.ok(rotaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RotaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rotaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RotaResponse> atualizar(@PathVariable Long id,
                                                  @RequestBody RotaRequest request) {
        return ResponseEntity.ok(rotaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        rotaService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}