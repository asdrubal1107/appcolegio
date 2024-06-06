package com.api.iberoamericana.colegio.service;

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
    public List<Curso> getCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso getCurso(long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(HttpStatus.NOT_FOUND,
                                "No se encontro el curso con id " + id));
    }

    @Override
    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso updateCurso(Curso curso, long id) {
        Curso cursoUpdate = getCurso(id);
        cursoUpdate.setNombre(curso.getNombre());
        cursoUpdate.setDescripcion(curso.getDescripcion());
        return cursoRepository.save(cursoUpdate);
    }

    @Override
    public String deleteCurso(long id) {
        Curso curso = getCurso(id);
        cursoRepository.delete(curso);
        return "Se elimino el curso con id " + id;
    }

}
