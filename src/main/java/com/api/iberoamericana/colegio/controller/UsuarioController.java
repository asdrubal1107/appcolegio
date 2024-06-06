package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.UsuarioRequest;
import com.api.iberoamericana.colegio.entity.Usuario;
import com.api.iberoamericana.colegio.service.RolService;
import com.api.iberoamericana.colegio.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable String id) {
        return usuarioService.getUsuario(Long.parseLong(id));
    }

    @PostMapping()
    public Usuario create(@Valid @RequestBody UsuarioRequest usuario) throws ParseException {
        return usuarioService.createUsuario(usuarioRequestToUsuario(usuario));
    }

    @PutMapping("/{id}")
    public Usuario update(@Valid @RequestBody UsuarioRequest usuario, @PathVariable String id) throws ParseException {
        return usuarioService.updateUsuario(usuarioRequestToUsuario(usuario), Long.parseLong(id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return usuarioService.deleteUsuario(Long.parseLong(id));
    }

    private Usuario usuarioRequestToUsuario(UsuarioRequest usuarioRequest) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        String dateInString = usuarioRequest.getFechaNacimiento();
        Date fechaNacimiento = formatter.parse(dateInString);
        return Usuario.builder()
                .documento(usuarioRequest.getDocumento())
                .nombres(usuarioRequest.getNombres())
                .apellidos(usuarioRequest.getApellidos())
                .fechaNacimiento(fechaNacimiento)
                .direccion(usuarioRequest.getDireccion())
                .celular(usuarioRequest.getCelular())
                .correoElectronico(usuarioRequest.getCorreoElectronico())
                .rol(usuarioService.getRolById(usuarioRequest.getIdRol()))
                .build();
    }

}
