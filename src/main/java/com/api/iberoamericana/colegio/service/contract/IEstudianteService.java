package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.controller.response.EstudianteResponse;
import com.api.iberoamericana.colegio.entity.Estudiante;
import com.api.iberoamericana.colegio.entity.Usuario;

import java.util.List;

public interface IEstudianteService {

    List<EstudianteResponse> getEstudiantes();

    EstudianteResponse getEstudiante(long id);

    EstudianteResponse createEstudiante(Estudiante estudiante);

    EstudianteResponse updateEstudiante(Estudiante estudiante, long id);

    String deleteEstudiante(long id);

    Usuario getUsuarioById(long id);

}
