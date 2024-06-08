package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.controller.response.MateriaProfesorResponse;

import java.util.List;

public interface IMateriaProfesorService {

    List<MateriaProfesorResponse> getAll();

}
