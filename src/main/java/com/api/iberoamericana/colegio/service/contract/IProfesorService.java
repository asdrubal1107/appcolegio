package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.entity.Profesor;

import java.util.List;

public interface IProfesorService {

    List<Profesor> getProfesores();

    Profesor getProfesor(long id);

    Profesor createProfesor(Profesor profesor);

    Profesor updateProfesor(Profesor profesor, long id);

    String deleteProfesor(long id);

}
