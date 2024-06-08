package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.response.ProfesorResponse;
import com.api.iberoamericana.colegio.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorResponse>> findAll(){
        return ResponseEntity.ok(profesorService.getProfesores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponse> findById(@PathVariable String id){
        return ResponseEntity.ok(profesorService.getProfesor(Long.parseLong(id)));
    }

}
