package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.controller.response.UsuarioResponse;
import com.api.iberoamericana.colegio.entity.Rol;
import com.api.iberoamericana.colegio.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<UsuarioResponse> getUsuarios();

    UsuarioResponse getUsuario(long id);

    UsuarioResponse createUsuario(Usuario usuario);

    UsuarioResponse updateUsuario(Usuario usuario, long id);

    String deleteUsuario(long id);

    Rol getRolById(long id);

}
