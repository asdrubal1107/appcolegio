package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.entity.Rol;

import java.util.List;

public interface IRolService {

    List<Rol> getRoles();

    Rol getRol(long id);

    Rol createRol(Rol curso);

    Rol updateRol(Rol curso, long id);

    String deleteRol(long id);

}
