package com.unibus.unibus_api.controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.unibus.unibus_api.dto.LoginRequest;
import com.unibus.unibus_api.dto.LoginResponse;
import com.unibus.unibus_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

}