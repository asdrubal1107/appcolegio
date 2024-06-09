package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.request.EstudianteRequest;
import com.api.iberoamericana.colegio.controller.response.EstudianteResponse;
import com.api.iberoamericana.colegio.controller.response.GrupoResponse;
import com.api.iberoamericana.colegio.controller.response.RolResponse;
import com.api.iberoamericana.colegio.controller.response.UsuarioResponse;
import com.api.iberoamericana.colegio.entity.Estudiante;
import com.api.iberoamericana.colegio.entity.Usuario;
import com.api.iberoamericana.colegio.exception.NotFoundException;
import com.api.iberoamericana.colegio.repository.EstudianteRepository;
import com.api.iberoamericana.colegio.repository.GrupoRepository;
import com.api.iberoamericana.colegio.repository.RolRepository;
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
    private final RolRepository rolRepository;
    private final GrupoRepository grupoRepository;

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
    public EstudianteResponse createEstudiante(EstudianteRequest estudiante) {
        Usuario usuarioSaved = usuarioRepository.save(toUsuario(estudiante));
        Estudiante estudianteSave = estudianteRepository.save(toEstudiante(estudiante, usuarioSaved));
        return estudianteToEstudianteResponse(estudianteSave);
    }

    @Override
    public EstudianteResponse updateEstudiante(EstudianteRequest estudiante, long id) {
        Estudiante estudianteUpdate = obtenerEstudiantePorId(id);
        Usuario usuarioUpdate = usuarioRepository.findById(estudianteUpdate.getUsuario().getIdUsuario())
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el usuario con id " + estudianteUpdate.getUsuario().getIdUsuario()));
        Usuario usuarioRequest = toUsuario(estudiante);
        usuarioUpdate.setNombres(usuarioRequest.getNombres());
        usuarioUpdate.setDocumento(usuarioRequest.getDocumento());
        usuarioUpdate.setCorreoElectronico(usuarioRequest.getCorreoElectronico());
        usuarioUpdate.setPassword(usuarioRequest.getPassword());
        usuarioUpdate.setRol(usuarioRequest.getRol());
        usuarioUpdate.setIdUsuario(estudianteUpdate.getUsuario().getIdUsuario());

        Estudiante estudianteRequest = toEstudiante(estudiante, usuarioUpdate);
        estudianteUpdate.setNombreAcudiente(estudianteRequest.getNombreAcudiente());
        estudianteUpdate.setUsuario(usuarioUpdate);
        estudianteUpdate.setGrupo(estudianteRequest.getGrupo());
        usuarioRepository.save(usuarioUpdate);
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

    private Estudiante toEstudiante(EstudianteRequest request, Usuario usuario){
        return Estudiante.builder()
                .nombreAcudiente(request.getNombreAcudiente())
                .usuario(usuario)
                .grupo(grupoRepository.findById(Long.parseLong(request.getIdGrupo()))
                        .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                                "No se encontro el grupo con id " + request.getIdGrupo())))
                .build();
    }

    private Usuario toUsuario(EstudianteRequest request){
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

    private EstudianteResponse estudianteToEstudianteResponse(Estudiante estudiante){
        return EstudianteResponse.builder()
                .id(estudiante.getIdEstudiante())
                .nombres(estudiante.getUsuario().getNombres())
                .documento(estudiante.getUsuario().getDocumento())
                .correoElectronico(estudiante.getUsuario().getCorreoElectronico())
                .contrasena(estudiante.getUsuario().getPassword())
                .nombreAcudiente(estudiante.getNombreAcudiente())
                .grupo(GrupoResponse.builder()
                        .id(estudiante.getGrupo().getIdGrupo())
                        .nombre(estudiante.getGrupo().getNombre())
                        .build())
                .rol(RolResponse.builder()
                        .id(estudiante.getUsuario().getRol().getIdRol())
                        .nombre(estudiante.getUsuario().getRol().getNombre())
                        .build())
                .build();
    }

}
