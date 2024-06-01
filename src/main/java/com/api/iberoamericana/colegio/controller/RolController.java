package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.RolRequest;
import com.api.iberoamericana.colegio.entity.Rol;
import com.api.iberoamericana.colegio.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping
    public List<Rol> getAll() {
        return rolService.getRoles();
    }

    @GetMapping("/{id}")
    public Rol getById(@PathVariable String id) {
        return rolService.getRol(Long.parseLong(id));
    }

    @PostMapping()
    public Rol create(@Valid @RequestBody RolRequest rol) {
        return rolService.createRol(rolRequestToRol(rol));
    }

    @PutMapping("/{id}")
    public Rol update(@Valid @RequestBody RolRequest rol, @PathVariable String id) {
        return rolService.updateRol(rolRequestToRol(rol), Long.parseLong(id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return rolService.deleteRol(Long.parseLong(id));
    }

    private Rol rolRequestToRol(RolRequest rolRequest) {
        return Rol.builder()
                .nombre(rolRequest.getNombre())
                .build();
    }

}
