package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.response.UsuarioResponse;
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
    public List<UsuarioResponse> getUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::usuarioToUsuarioResponse)
                .toList();
    }

    @Override
    public UsuarioResponse getUsuario(long id) {
        return usuarioToUsuarioResponse(obtenerUsuarioPorId(id));
    }

    @Override
    public UsuarioResponse createUsuario(Usuario usuario) {
        usuario.setEstado(Boolean.TRUE);
        return usuarioToUsuarioResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponse updateUsuario(Usuario usuario, long id) {
        Usuario usuarioUpdate = obtenerUsuarioPorId(id);
        usuarioUpdate.setDocumento(usuario.getDocumento());
        usuarioUpdate.setNombres(usuario.getNombres());
        usuarioUpdate.setApellidos(usuario.getApellidos());
        usuarioUpdate.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioUpdate.setDireccion(usuario.getDireccion());
        usuarioUpdate.setCelular(usuario.getCelular());
        usuarioUpdate.setCorreoElectronico(usuario.getCorreoElectronico());
        usuarioUpdate.setRol(usuario.getRol());
        return usuarioToUsuarioResponse(usuarioRepository.save(usuarioUpdate));
    }

    @Override
    public String deleteUsuario(long id) {
        Usuario usuarioDelete = obtenerUsuarioPorId(id);
        usuarioRepository.delete(usuarioDelete);
        return "Se elimino el usuario con id " + id;
    }

    @Override
    public Rol getRolById(long id){
        return rolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el rol con id " + id));
    }

    private Usuario obtenerUsuarioPorId(long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(HttpStatus.NOT_FOUND,
                                "No se encontro el usuario con id " + id));
    }

    private UsuarioResponse usuarioToUsuarioResponse(Usuario usuario){
        return UsuarioResponse.builder()
                .id(usuario.getIdUsuario())
                .documento(usuario.getDocumento())
                .nombres(usuario.getNombres())
                .apellidos(usuario.getApellidos())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .direccion(usuario.getDireccion())
                .celular(usuario.getCelular())
                .correoElectronico(usuario.getCorreoElectronico())
                .estado(usuario.getEstado() ? "Activo" : "Inactivo")
                .rol(usuario.getRol().getNombre())
                .build();
    }

}
