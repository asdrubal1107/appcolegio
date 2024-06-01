package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.entity.Curso;

import java.util.List;

public interface ICursoService {

    List<Curso> getCursos();

    Curso getCurso(long id);

    Curso createCurso(Curso curso);

    Curso updateCurso(Curso curso, long id);

    String deleteCurso(long id);

}
