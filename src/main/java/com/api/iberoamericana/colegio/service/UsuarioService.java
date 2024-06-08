package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.response.LoginResponse;
import com.api.iberoamericana.colegio.controller.response.RolResponse;
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
        return usuarioToUsuarioResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponse updateUsuario(Usuario usuario, long id) {
        Usuario usuarioUpdate = obtenerUsuarioPorId(id);
        usuarioUpdate.setDocumento(usuario.getDocumento());
        usuarioUpdate.setNombres(usuario.getNombres());
        usuarioUpdate.setCorreoElectronico(usuario.getCorreoElectronico());
        usuarioUpdate.setPassword(usuario.getPassword());
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

    @Override
    public LoginResponse findByCorreoElectronicoAndPassword(String correoElectronico, String password) {
        Usuario usuario = usuarioRepository
                .findByCorreoElectronicoAndPassword(correoElectronico, password)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "Usuario o contraseña incorrecta"));
        return LoginResponse.builder()
                .nombres(usuario.getNombres())
                .rol(RolResponse.builder()
                        .id(usuario.getRol().getIdRol())
                        .nombre(usuario.getRol().getNombre())
                        .build())
                .build();
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
                .correoElectronico(usuario.getCorreoElectronico())
                .password(usuario.getPassword())
                .rol(usuario.getRol().getNombre())
                .build();
    }

}
