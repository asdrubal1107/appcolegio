package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.controller.request.CursosAsignadosRequest;
import com.api.iberoamericana.colegio.controller.response.CursosAsignadosResponse;
import com.api.iberoamericana.colegio.entity.Curso;
import com.api.iberoamericana.colegio.entity.CursosAsignados;
import com.api.iberoamericana.colegio.entity.Estudiante;
import com.api.iberoamericana.colegio.entity.Profesor;

import java.util.List;

public interface ICursosAsignadosService {

    List<CursosAsignadosResponse> getAllCursosAsignados();

    CursosAsignadosResponse getCursoAsignado(long id);

    CursosAsignadosResponse createCursoAsignado(CursosAsignados request);

    CursosAsignadosResponse updateCursoAsignado(CursosAsignados request, long id);

    String deleteCursoAsignado(long id);

    Profesor getProfesorById(long id);

    Curso getCursoById(long id);

    Estudiante getEstudianteById(long id);

}
