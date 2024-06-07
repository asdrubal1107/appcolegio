package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.CursoRequest;
import com.api.iberoamericana.colegio.controller.response.CursoResponse;
import com.api.iberoamericana.colegio.entity.Curso;
import com.api.iberoamericana.colegio.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoResponse>> getAll() {
        return new ResponseEntity<>(cursoService.getCursos(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> getById(@PathVariable String id) {
        return new ResponseEntity<>(cursoService.getCurso(Long.parseLong(id)),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CursoResponse> create(@Valid @RequestBody CursoRequest curso) {
        return new ResponseEntity<>(cursoService.createCurso(cursoRequestToCurso(curso)),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> update(@Valid @RequestBody CursoRequest curso, @PathVariable String id) {
        return new ResponseEntity<>(cursoService.updateCurso(cursoRequestToCurso(curso), Long.parseLong(id)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return new ResponseEntity<>(cursoService.deleteCurso(Long.parseLong(id)),
                HttpStatus.OK);
    }

    private Curso cursoRequestToCurso(CursoRequest cursoRequest) {
        return Curso.builder()
                .nombre(cursoRequest.getNombre())
                .descripcion(cursoRequest.getDescripcion())
                .build();
    }

}
