package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.response.EstudianteResponse;
import com.api.iberoamericana.colegio.entity.Estudiante;
import com.api.iberoamericana.colegio.entity.Usuario;
import com.api.iberoamericana.colegio.exception.NotFoundException;
import com.api.iberoamericana.colegio.repository.EstudianteRepository;
import com.api.iberoamericana.colegio.repository.UsuarioRepository;
import com.api.iberoamericana.colegio.service.contract.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudianteService implements IEstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<EstudianteResponse> getEstudiantes() {
        return estudianteRepository.findAll()
                .stream()
                .map(this::estudianteToEstudianteResponse)
                .toList();
    }

    @Override
    public EstudianteResponse getEstudiante(long id) {
        return estudianteToEstudianteResponse(obtenerEstudiantePorId(id));
    }

    @Override
    public EstudianteResponse createEstudiante(Estudiante estudiante) {
        return estudianteToEstudianteResponse(estudianteRepository.save(estudiante));
    }

    @Override
    public EstudianteResponse updateEstudiante(Estudiante estudiante, long id) {
        Estudiante estudianteUpdate = obtenerEstudiantePorId(id);
        estudianteUpdate.setNombreAcudiente(estudiante.getNombreAcudiente());
        estudianteUpdate.setUsuario(estudiante.getUsuario());
        return estudianteToEstudianteResponse(estudianteRepository.save(estudianteUpdate));
    }

    @Override
    public String deleteEstudiante(long id) {
        Estudiante estudianteDelete = obtenerEstudiantePorId(id);
        estudianteRepository.delete(estudianteDelete);
        return "Se elimino el estudiante con id " + id;
    }

    @Override
    public Usuario getUsuarioById(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el usuario con id " + id));
    }

    private Estudiante obtenerEstudiantePorId(long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el estudiante con id " + id));
    }

    private EstudianteResponse estudianteToEstudianteResponse(Estudiante estudiante){
        return EstudianteResponse.builder()
                .id(estudiante.getIdEstudiante())
                .nombre(estudiante.getUsuario().getNombres())
                .correoElectronico(estudiante.getUsuario().getCorreoElectronico())
                .nombreAcudiente(estudiante.getNombreAcudiente())
                .build();
    }

}
