package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.ProfesorRequest;
import com.api.iberoamericana.colegio.entity.Profesor;
import com.api.iberoamericana.colegio.service.ProfesorService;
import com.api.iberoamericana.colegio.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;
    private final UsuarioService usuarioService;

    @GetMapping
    public List<Profesor> getAll() {
        return profesorService.getProfesores();
    }

    @GetMapping("/{id}")
    public Profesor getById(@PathVariable String id) {
        return profesorService.getProfesor(Long.parseLong(id));
    }

    @PostMapping
    public Profesor create(@Valid @RequestBody ProfesorRequest profesorRequest)  {
        return profesorService.createProfesor(profesorRequestToProfesor(profesorRequest));
    }

    @PutMapping("/{id}")
    public Profesor update(@Valid @RequestBody ProfesorRequest profesorRequest, @PathVariable String id) {
        return profesorService.updateProfesor(profesorRequestToProfesor(profesorRequest),
                Long.parseLong(id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return usuarioService.deleteUsuario(Long.parseLong(id));
    }

    private Profesor profesorRequestToProfesor(ProfesorRequest profesorRequest) {
        return Profesor.builder()
                .materiasFuertes(profesorRequest.getMateriasFuertes())
                .materiasDebiles(profesorRequest.getMateriasDebiles())
                .usuario(usuarioService.getUsuario(profesorRequest.getIdUsuario()))
                .build();
    }

}
