package com.api.iberoamericana.colegio.service.contract;

import com.api.iberoamericana.colegio.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> getUsuarios();

    Usuario getUsuario(long id);

    Usuario createUsuario(Usuario usuario);

    Usuario updateUsuario(Usuario usuario, long id);

    String deleteUsuario(long id);

}
