package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.controller.response.CursoResponse;
import com.api.iberoamericana.colegio.entity.Curso;

import java.util.List;

public interface ICursoService {

    List<CursoResponse> getCursos();

    CursoResponse getCurso(long id);

    CursoResponse createCurso(Curso curso);

    CursoResponse updateCurso(Curso curso, long id);

    String deleteCurso(long id);

}
