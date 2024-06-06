package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.response.CursoResponse;
import com.api.iberoamericana.colegio.entity.Curso;
import com.api.iberoamericana.colegio.exception.NotFoundException;
import com.api.iberoamericana.colegio.repository.CursoRepository;
import com.api.iberoamericana.colegio.service.contract.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService implements ICursoService {

    private final CursoRepository cursoRepository;

    @Override
    public List<CursoResponse> getCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(this::cursoToCursoResponse)
                .toList();
    }

    @Override
    public CursoResponse getCurso(long id) {
        return cursoToCursoResponse(obtenerCursoPorId(id));
    }

    @Override
    public CursoResponse createCurso(Curso curso) {
        return cursoToCursoResponse(cursoRepository.save(curso));
    }

    @Override
    public CursoResponse updateCurso(Curso curso, long id) {
        Curso cursoUpdate = obtenerCursoPorId(id);
        cursoUpdate.setNombre(curso.getNombre());
        cursoUpdate.setDescripcion(curso.getDescripcion());
        return cursoToCursoResponse(cursoRepository.save(cursoUpdate));
    }

    @Override
    public String deleteCurso(long id) {
        Curso curso = obtenerCursoPorId(id);
        cursoRepository.delete(curso);
        return "Se elimino el curso con id " + id;
    }

    private Curso obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(HttpStatus.NOT_FOUND,
                                "No se encontro el curso con id " + id));
    }

    private CursoResponse cursoToCursoResponse(Curso curso) {
        return CursoResponse.builder()
                .id(curso.getIdCurso())
                .nombre(curso.getNombre())
                .descripcion(curso.getDescripcion())
                .build();
    }

}
