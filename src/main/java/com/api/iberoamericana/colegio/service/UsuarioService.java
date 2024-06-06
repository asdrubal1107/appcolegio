package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.entity.Rol;
import com.api.iberoamericana.colegio.entity.Usuario;
import com.api.iberoamericana.colegio.exception.NotFoundException;
import com.api.iberoamericana.colegio.repository.RolRepository;
import com.api.iberoamericana.colegio.repository.UsuarioRepository;
import com.api.iberoamericana.colegio.service.contract.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuario(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(HttpStatus.NOT_FOUND,
                                "No se encontro el usuario con id " + id));
    }

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario, long id) {
        Usuario usuarioUpdate = getUsuario(id);
        usuarioUpdate.setDocumento(usuario.getDocumento());
        usuarioUpdate.setNombres(usuario.getNombres());
        usuarioUpdate.setApellidos(usuario.getApellidos());
        usuarioUpdate.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioUpdate.setDireccion(usuario.getDireccion());
        usuarioUpdate.setCelular(usuario.getCelular());
        usuarioUpdate.setCorreoElectronico(usuario.getCorreoElectronico());
        usuarioUpdate.setRol(usuario.getRol());
        return usuarioRepository.save(usuarioUpdate);
    }

    @Override
    public String deleteUsuario(long id) {
        Usuario usuarioDelete = getUsuario(id);
        usuarioRepository.delete(usuarioDelete);
        return "Se elimino el usuario con id " + id;
    }

    public Rol getRolById(long id){
        return rolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el rol con id " + id));
    }

}
