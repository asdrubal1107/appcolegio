package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.EstudianteRequest;
import com.api.iberoamericana.colegio.controller.response.EstudianteResponse;
import com.api.iberoamericana.colegio.controller.response.UsuarioResponse;
import com.api.iberoamericana.colegio.entity.Estudiante;
import com.api.iberoamericana.colegio.entity.Usuario;
import com.api.iberoamericana.colegio.service.EstudianteService;
import com.api.iberoamericana.colegio.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/estudiantes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<EstudianteResponse>> getAll() {
        return ResponseEntity.ok(estudianteService.getEstudiantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(estudianteService.getEstudiante(Long.parseLong(id)));
    }

    @PostMapping
    public ResponseEntity<EstudianteResponse> create(@Valid @RequestBody EstudianteRequest estudianteRequest)  {
        EstudianteResponse estudianteResponse = estudianteService.createEstudiante(estudianteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponse> update(@Valid @RequestBody EstudianteRequest estudianteRequest,
                                                     @PathVariable String id) {
        EstudianteResponse estudianteResponse =  estudianteService
                .updateEstudiante(estudianteRequest, Long.parseLong(id));
        return ResponseEntity.ok(estudianteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok(estudianteService.deleteEstudiante(Long.parseLong(id)));
    }

}
