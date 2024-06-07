package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.CursosAsignadosRequest;
import com.api.iberoamericana.colegio.controller.response.CursosAsignadosResponse;
import com.api.iberoamericana.colegio.entity.CursosAsignados;
import com.api.iberoamericana.colegio.service.CursosAsignadosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos/asignados")
@RequiredArgsConstructor
public class CursosAsignadosController {

    private final CursosAsignadosService cursosAsignadosService;

    @GetMapping
    public ResponseEntity<List<CursosAsignadosResponse>> findAll(){
        return ResponseEntity.ok(cursosAsignadosService.getAllCursosAsignados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursosAsignadosResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(cursosAsignadosService.getCursoAsignado(id));
    }

    @PostMapping
    public ResponseEntity<CursosAsignadosResponse> save(@RequestBody CursosAsignadosRequest request){
        CursosAsignadosResponse response = cursosAsignadosService
                .createCursoAsignado(toCursosAsignados(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursosAsignadosResponse> update(@RequestBody CursosAsignadosRequest request,
                                                          @PathVariable Long id){
        CursosAsignadosResponse response = cursosAsignadosService
                .updateCursoAsignado(toCursosAsignados(request), id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(cursosAsignadosService.deleteCursoAsignado(id));
    }

    private CursosAsignados toCursosAsignados(CursosAsignadosRequest request){
        return CursosAsignados.builder()
                .curso(cursosAsignadosService.getCursoById(Long.parseLong(request.getIdCurso())))
                .estudiante(cursosAsignadosService.getEstudianteById(Long.parseLong(request.getIdEstudiante())))
                .profesor(cursosAsignadosService.getProfesorById(Long.parseLong(request.getIdProfesor())))
                .build();
    }

}
