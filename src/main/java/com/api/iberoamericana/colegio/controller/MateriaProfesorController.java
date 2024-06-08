package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.response.MateriaProfesorResponse;
import com.api.iberoamericana.colegio.service.MateriaProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/materias/profesores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MateriaProfesorController {

    private final MateriaProfesorService service;

    @GetMapping
    public ResponseEntity<List<MateriaProfesorResponse>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

}
