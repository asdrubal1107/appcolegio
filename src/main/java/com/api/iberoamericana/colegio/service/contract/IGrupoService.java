package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.controller.response.GrupoResponse;

import java.util.List;

public interface IGrupoService {

    List<GrupoResponse> getAll();

}
