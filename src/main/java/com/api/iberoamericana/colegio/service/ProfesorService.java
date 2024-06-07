package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.response.ProfesorResponse;
import com.api.iberoamericana.colegio.entity.Profesor;
import com.api.iberoamericana.colegio.entity.Usuario;
import com.api.iberoamericana.colegio.exception.NotFoundException;
import com.api.iberoamericana.colegio.repository.ProfesorRepository;
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
    public ProfesorResponse createProfesor(Profesor profesor) {
        return profesorToProfesorResponse(profesorRepository.save(profesor));
    }

    @Override
    public ProfesorResponse updateProfesor(Profesor profesor, long id) {
        Profesor profesorUpdate = obtenerProfesorPorId(id);
        profesorUpdate.setMateriasFuertes(profesor.getMateriasFuertes());
        profesorUpdate.setMateriasDebiles(profesor.getMateriasDebiles());
        profesorUpdate.setUsuario(profesor.getUsuario());
        return profesorToProfesorResponse(profesorRepository.save(profesorUpdate));
    }

    @Override
    public String deleteProfesor(long id) {
        Profesor profesorDelete = obtenerProfesorPorId(id);
        profesorRepository.delete(profesorDelete);
        return "Se elimino el profesor con id " + id;
    }

    @Override
    public Usuario getUsuarioById(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el usuario con id " + id));
    }

    private Profesor obtenerProfesorPorId(long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                                "No se encontro el profesor con id " + id));
    }

    private ProfesorResponse profesorToProfesorResponse(Profesor profesor){
        return ProfesorResponse.builder()
                .id(profesor.getIdProfesor())
                .nombre(profesor.getUsuario().getNombres())
                .apellido(profesor.getUsuario().getApellidos())
                .celular(profesor.getUsuario().getCelular())
                .correoElectronico(profesor.getUsuario().getCorreoElectronico())
                .materiasFuertes(profesor.getMateriasFuertes())
                .materiasDebiles(profesor.getMateriasDebiles())
                .build();
    }

}
