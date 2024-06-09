package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.controller.request.ProfesorRequest;
import com.api.iberoamericana.colegio.controller.response.ProfesorResponse;
import com.api.iberoamericana.colegio.entity.Profesor;
import com.api.iberoamericana.colegio.entity.Usuario;

import java.util.List;

public interface IProfesorService {

    List<ProfesorResponse> getProfesores();

    ProfesorResponse getProfesor(long id);

    ProfesorResponse createProfesor(ProfesorRequest profesor);

    ProfesorResponse updateProfesor(ProfesorRequest profesor, long id);

    String deleteProfesor(long id);

    Usuario getUsuarioById(long id);

}
