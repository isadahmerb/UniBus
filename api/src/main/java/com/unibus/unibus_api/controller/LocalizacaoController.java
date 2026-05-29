package com.unibus.unibus_api.controller;

import com.unibus.unibus_api.dto.LocalizacaoRequest;
import com.unibus.unibus_api.dto.LocalizacaoResponse;
import com.unibus.unibus_api.service.LocalizacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viagens")
@RequiredArgsConstructor
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    @PostMapping("/{viagemId}/localizacao")
    public ResponseEntity<LocalizacaoResponse> atualizar(@PathVariable Long viagemId,
                                                         @RequestBody LocalizacaoRequest request) {
        return ResponseEntity.ok(localizacaoService.atualizar(viagemId, request));
    }

    @GetMapping("/{viagemId}/localizacao")
    public ResponseEntity<LocalizacaoResponse> obterUltima(@PathVariable Long viagemId) {
        return ResponseEntity.ok(localizacaoService.obterUltima(viagemId));
    }
}