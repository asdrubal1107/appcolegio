package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.request.EstudianteRequest;
import com.api.iberoamericana.colegio.controller.request.ProfesorRequest;
import com.api.iberoamericana.colegio.controller.response.ProfesorResponse;
import com.api.iberoamericana.colegio.controller.response.RolResponse;
import com.api.iberoamericana.colegio.entity.Profesor;
import com.api.iberoamericana.colegio.entity.Usuario;
import com.api.iberoamericana.colegio.exception.NotFoundException;
import com.api.iberoamericana.colegio.repository.ProfesorRepository;
import com.api.iberoamericana.colegio.repository.RolRepository;
import com.api.iberoamericana.colegio.repository.UsuarioRepository;
import com.api.iberoamericana.colegio.service.contract.IProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorService implements IProfesorService {

    private final ProfesorRepository profesorRepository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    public List<ProfesorResponse> getProfesores() {
        return profesorRepository.findAll()
                .stream()
                .map(this::profesorToProfesorResponse)
                .toList();
    }

    @Override
    public ProfesorResponse getProfesor(long id) {
        return profesorToProfesorResponse(obtenerProfesorPorId(id));
    }

    @Override
    public ProfesorResponse createProfesor(ProfesorRequest request) {
        Usuario usuarioSaved = usuarioRepository.save(toUsuario(request));
        return profesorToProfesorResponse(profesorRepository
                .save(Profesor.builder()
                        .usuario(usuarioSaved)
                        .build()));
    }

    @Override
    public ProfesorResponse updateProfesor(ProfesorRequest request, long id) {
        Profesor profesorUpdate = obtenerProfesorPorId(id);
        Usuario usuarioRequest = toUsuario(request);
        usuarioRequest.setIdUsuario(profesorUpdate.getUsuario().getIdUsuario());
        profesorUpdate.setUsuario(usuarioRequest);
        usuarioRepository.save(usuarioRequest);
        return profesorToProfesorResponse(profesorRepository.save(profesorUpdate));
    }

    @Override
    public String deleteProfesor(long id) {
        Profesor profesorDelete = obtenerProfesorPorId(id);
        Long idUsuario = profesorDelete.getUsuario().getIdUsuario();
        profesorRepository.delete(profesorDelete);
        usuarioRepository.deleteById(idUsuario);
        return "Se elimino el profesor con id " + id;
    }

    @Override
    public Usuario getUsuarioById(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el usuario con id " + id));
    }

    private Usuario toUsuario(ProfesorRequest request){
        return Usuario.builder()
                .documento(request.getDocumento())
                .nombres(request.getNombres())
                .correoElectronico(request.getCorreoElectronico())
                .password(request.getPassword())
                .rol(rolRepository.findById(Long.parseLong(request.getIdRol()))
                        .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                                "No se encontro rol con id " + request.getIdRol())))
                .build();
    }

    private Profesor obtenerProfesorPorId(long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                                "No se encontro el profesor con id " + id));
    }

    private ProfesorResponse profesorToProfesorResponse(Profesor profesor){
        return ProfesorResponse.builder()
                .id(profesor.getIdProfesor())
                .nombres(profesor.getUsuario().getNombres())
                .documento(profesor.getUsuario().getDocumento())
                .correoElectronico(profesor.getUsuario().getCorreoElectronico())
                .contrasena(profesor.getUsuario().getPassword())
                .rol(RolResponse.builder()
                        .id(profesor.getUsuario().getRol().getIdRol())
                        .nombre(profesor.getUsuario().getRol().getNombre())
                        .build())
                .build();
    }

}
