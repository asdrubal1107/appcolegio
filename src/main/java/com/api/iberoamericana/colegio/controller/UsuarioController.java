package com.api.iberoamericana.colegio.controller;

import com.api.iberoamericana.colegio.controller.request.UsuarioRequest;
import com.api.iberoamericana.colegio.controller.response.UsuarioResponse;
import com.api.iberoamericana.colegio.entity.Usuario;
import com.api.iberoamericana.colegio.service.RolService;
import com.api.iberoamericana.colegio.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<List<UsuarioResponse>> getAll() {
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.getUsuario(Long.parseLong(id)));
    }

    @PostMapping()
    public ResponseEntity<UsuarioResponse> create(@Valid @RequestBody UsuarioRequest usuario)
            throws ParseException {
        UsuarioResponse usuarioResponse = usuarioService.createUsuario(usuarioRequestToUsuario(usuario));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(@Valid @RequestBody UsuarioRequest usuario, @PathVariable String id)
            throws ParseException {
        UsuarioResponse usuarioResponse = usuarioService.updateUsuario(usuarioRequestToUsuario(usuario), Long.parseLong(id));
        return ResponseEntity.ok(usuarioResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.deleteUsuario(Long.parseLong(id)));
    }

    private Usuario usuarioRequestToUsuario(UsuarioRequest usuarioRequest)
            throws ParseException {
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
                .rol(usuarioService.getRolById(Long.parseLong(usuarioRequest.getIdRol())))
                .build();
    }

}
