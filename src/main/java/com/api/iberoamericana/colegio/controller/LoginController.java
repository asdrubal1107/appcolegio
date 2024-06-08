package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.LoginRequest;
import com.api.iberoamericana.colegio.controller.response.LoginResponse;
import com.api.iberoamericana.colegio.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<LoginResponse> getLogin(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = usuarioService
                .findByCorreoElectronicoAndPassword(loginRequest.getCorreoElectronico(),
                        loginRequest.getPassword());
        return ResponseEntity.ok(response);
    }

}
