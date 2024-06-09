package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.ProfesorRequest;
import com.api.iberoamericana.colegio.controller.response.ProfesorResponse;
import com.api.iberoamericana.colegio.service.ProfesorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<ProfesorResponse> save(@Valid @RequestBody ProfesorRequest profesorRequest){
        ProfesorResponse response = profesorService.createProfesor(profesorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorResponse> save(@Valid @RequestBody ProfesorRequest profesorRequest,
                                                 @PathVariable String id){
        ProfesorResponse response = profesorService.updateProfesor(profesorRequest, Long.parseLong(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok(profesorService.deleteProfesor(Long.parseLong(id)));
    }

}
