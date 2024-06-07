package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.controller.response.RolResponse;
import com.api.iberoamericana.colegio.entity.Rol;

import java.util.List;

public interface IRolService {

    List<RolResponse> getRoles();

    RolResponse getRol(long id);

    RolResponse createRol(Rol curso);

    RolResponse updateRol(Rol curso, long id);

    String deleteRol(long id);

}
