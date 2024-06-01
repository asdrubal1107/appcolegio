package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.CursoRequest;
import com.api.iberoamericana.colegio.entity.Curso;
import com.api.iberoamericana.colegio.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public List<Curso> getAll() {
        return cursoService.getCursos();
    }

    @GetMapping("/{id}")
    public Curso getById(@PathVariable String id) {
        return cursoService.getCurso(Long.parseLong(id));
    }

    @PostMapping()
    public Curso create(@Valid @RequestBody CursoRequest curso) {
        return cursoService.createCurso(cursoRequestToCurso(curso));
    }

    @PutMapping("/{id}")
    public Curso update(@Valid @RequestBody CursoRequest curso, @PathVariable String id) {
        return cursoService.updateCurso(cursoRequestToCurso(curso), Long.parseLong(id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return cursoService.deleteCurso(Long.parseLong(id));
    }

    private Curso cursoRequestToCurso(CursoRequest cursoRequest) {
        return Curso.builder()
                .nombre(cursoRequest.getNombre())
                .descripcion(cursoRequest.getDescripcion())
                .build();
    }

}
