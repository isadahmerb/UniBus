package com.unibus.unibus_api.controller;

import com.unibus.unibus_api.dto.MotoristaRequest;
import com.unibus.unibus_api.dto.MotoristaResponse;
import com.unibus.unibus_api.service.MotoristaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
@RequiredArgsConstructor
public class MotoristaController {

    private final MotoristaService motoristaService;

    @PostMapping
    public ResponseEntity<MotoristaResponse> criar(@RequestBody MotoristaRequest request) {
        return ResponseEntity.status(201).body(motoristaService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<MotoristaResponse>> listar() {
        return ResponseEntity.ok(motoristaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoristaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoristaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoristaResponse> atualizar(@PathVariable Long id,
                                                       @RequestBody MotoristaRequest request) {
        return ResponseEntity.ok(motoristaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        motoristaService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}