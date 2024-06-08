package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.response.ProfesorResponse;
import com.api.iberoamericana.colegio.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
