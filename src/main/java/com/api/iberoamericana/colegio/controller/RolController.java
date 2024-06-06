package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.RolRequest;
import com.api.iberoamericana.colegio.controller.response.RolResponse;
import com.api.iberoamericana.colegio.entity.Rol;
import com.api.iberoamericana.colegio.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping
    public ResponseEntity<List<RolResponse>> getAll() {
        return new ResponseEntity<>(rolService.getRoles(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolResponse> getById(@PathVariable String id) {
        return new ResponseEntity<>(rolService.getRol(Long.parseLong(id)),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RolResponse> create(@Valid @RequestBody RolRequest rol) {
        return new ResponseEntity<>(rolService.createRol(rolRequestToRol(rol)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolResponse> update(@Valid @RequestBody RolRequest rol, @PathVariable String id) {
        return new ResponseEntity<>(rolService.updateRol(rolRequestToRol(rol), Long.parseLong(id)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return new ResponseEntity<>(rolService.deleteRol(Long.parseLong(id)), HttpStatus.OK);
    }

    private Rol rolRequestToRol(RolRequest rolRequest) {
        return Rol.builder()
                .nombre(rolRequest.getNombre())
                .build();
    }

}
