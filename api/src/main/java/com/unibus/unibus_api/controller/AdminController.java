package com.unibus.unibus_api.controller;

import com.unibus.unibus_api.dto.AdminPerfilResponse;
import com.unibus.unibus_api.dto.AdminUpdateRequest;
import com.unibus.unibus_api.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/perfil")
    public ResponseEntity<AdminPerfilResponse> getPerfil(Authentication authentication) {
        return ResponseEntity.ok(adminService.getPerfil(authentication.getName()));
    }

    @PutMapping("/perfil")
    public ResponseEntity<AdminPerfilResponse> updatePerfil(
            Authentication authentication,
            @RequestBody AdminUpdateRequest request) {
        return ResponseEntity.ok(adminService.updatePerfil(authentication.getName(), request));
    }
}