package com.unibus.unibus_api.controller;

import com.unibus.unibus_api.dto.PassageiroRequest;
import com.unibus.unibus_api.dto.PassageiroResponse;
import com.unibus.unibus_api.service.PassageiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passageiros")
@RequiredArgsConstructor
public class PassageiroController {

    private final PassageiroService passageiroService;

    @PostMapping
    public ResponseEntity<PassageiroResponse> criar(@RequestBody PassageiroRequest request) {
        return ResponseEntity.status(201).body(passageiroService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<PassageiroResponse>> listar() {
        return ResponseEntity.ok(passageiroService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassageiroResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(passageiroService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassageiroResponse> atualizar(@PathVariable Long id,
                                                        @RequestBody PassageiroRequest request) {
        return ResponseEntity.ok(passageiroService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desativar(@PathVariable Long id) {
        passageiroService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}