package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.ProfesorRequest;
import com.api.iberoamericana.colegio.controller.response.ProfesorResponse;
import com.api.iberoamericana.colegio.entity.Profesor;
import com.api.iberoamericana.colegio.service.ProfesorService;
import com.api.iberoamericana.colegio.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorResponse>> getAll() {
        return ResponseEntity.ok(profesorService.getProfesores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(profesorService.getProfesor(Long.parseLong(id)));
    }

    @PostMapping
    public ResponseEntity<ProfesorResponse> create(@Valid @RequestBody ProfesorRequest profesorRequest)  {
        ProfesorResponse profesorResponse = profesorService.createProfesor(profesorRequestToProfesor(profesorRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(profesorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorResponse> update(@Valid @RequestBody ProfesorRequest profesorRequest, @PathVariable String id) {
        ProfesorResponse profesorResponse =  profesorService.updateProfesor(profesorRequestToProfesor(profesorRequest), Long.parseLong(id));
        return ResponseEntity.ok(profesorResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok(profesorService.deleteProfesor(Long.parseLong(id)));
    }

    private Profesor profesorRequestToProfesor(ProfesorRequest profesorRequest) {
        return Profesor.builder()
                .materiasFuertes(profesorRequest.getMateriasFuertes())
                .materiasDebiles(profesorRequest.getMateriasDebiles())
                .usuario(profesorService.getUsuarioById(Long.parseLong(profesorRequest.getIdUsuario())))
                .build();
    }

}
