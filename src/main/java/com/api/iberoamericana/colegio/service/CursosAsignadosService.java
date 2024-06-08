package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.response.CursosAsignadosResponse;
import com.api.iberoamericana.colegio.entity.Curso;
import com.api.iberoamericana.colegio.entity.CursosAsignados;
import com.api.iberoamericana.colegio.entity.Estudiante;
import com.api.iberoamericana.colegio.entity.Profesor;
import com.api.iberoamericana.colegio.exception.NotFoundException;
import com.api.iberoamericana.colegio.repository.CursoRepository;
import com.api.iberoamericana.colegio.repository.CursosAsignadosRepository;
import com.api.iberoamericana.colegio.repository.EstudianteRepository;
import com.api.iberoamericana.colegio.repository.ProfesorRepository;
import com.api.iberoamericana.colegio.service.contract.ICursosAsignadosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CursosAsignadosService implements ICursosAsignadosService {

    private final CursosAsignadosRepository cursosAsignadosRepository;
    private final EstudianteRepository estudianteRepository;
    private final ProfesorRepository profesorRepository;
    private final CursoRepository cursoRepository;

    @Override
    public List<CursosAsignadosResponse> getAllCursosAsignados() {
        return cursosAsignadosRepository.findAll()
                .stream()
                .map(this::cursosAsignadosToCursosAsignadosResponse)
                .toList();
    }

    @Override
    public CursosAsignadosResponse getCursoAsignado(long id) {
        return cursosAsignadosToCursosAsignadosResponse(obtenerCursoAsignadoPorId(id));
    }

    @Override
    public CursosAsignadosResponse createCursoAsignado(CursosAsignados cursosAsignados) {
        SimpleDateFormat format = new SimpleDateFormat ("dd-mm-yyyy");
        cursosAsignados.setPromedio(0.00);
        cursosAsignados.setFechaAsignacion(format.format(new Date()));
        return cursosAsignadosToCursosAsignadosResponse(cursosAsignadosRepository.save(cursosAsignados));
    }

    @Override
    public CursosAsignadosResponse updateCursoAsignado(CursosAsignados cursosAsignados, long id) {
        CursosAsignados cursosAsignadosUpdate = obtenerCursoAsignadoPorId(id);
        cursosAsignadosUpdate.setCurso(cursosAsignados.getCurso());
        cursosAsignadosUpdate.setEstudiante(cursosAsignados.getEstudiante());
        cursosAsignadosUpdate.setProfesor(cursosAsignados.getProfesor());
        return cursosAsignadosToCursosAsignadosResponse(cursosAsignadosRepository.save(cursosAsignadosUpdate));
    }

    @Override
    public String deleteCursoAsignado(long id) {
        CursosAsignados cursosAsignadosDelete = obtenerCursoAsignadoPorId(id);
        cursosAsignadosRepository.delete(cursosAsignadosDelete);
        return "Se elimino el curso asignado con id " + id;
    }

    @Override
    public Profesor getProfesorById(long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el profesor con id " + id));
    }

    @Override
    public Curso getCursoById(long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el curso con id " + id));
    }

    @Override
    public Estudiante getEstudianteById(long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el estudiante con id " + id));
    }

    private CursosAsignados obtenerCursoAsignadoPorId(long id){
        return cursosAsignadosRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el curso asignado con id " + id));
    }

    private CursosAsignadosResponse cursosAsignadosToCursosAsignadosResponse(CursosAsignados cursoAsignado){
        return CursosAsignadosResponse.builder()
                .idCursoAsignado(cursoAsignado.getIdCursoAsignado())
                .fechaAsignacion(cursoAsignado.getFechaAsignacion())
                .promedio(cursoAsignado.getPromedio())
                .curso(cursoAsignado.getCurso().getNombre())
                .estudiante(cursoAsignado.getEstudiante().getUsuario().getNombres())
                .profesor(cursoAsignado.getProfesor().getUsuario().getNombres())
                .build();
    }

}
