package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.entity.Profesor;
import com.api.iberoamericana.colegio.exception.NotFoundException;
import com.api.iberoamericana.colegio.repository.ProfesorRepository;
import com.api.iberoamericana.colegio.service.contract.IProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorService implements IProfesorService {

    private final ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> getProfesores() {
        return profesorRepository.findAll();
    }

    @Override
    public Profesor getProfesor(long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(HttpStatus.NOT_FOUND, "No se encontro el profesor con id " + id));
    }

    @Override
    public Profesor createProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public Profesor updateProfesor(Profesor profesor, long id) {
        Profesor profesorUpdate = getProfesor(id);
        profesorUpdate.setMateriasFuertes(profesor.getMateriasFuertes());
        profesorUpdate.setMateriasDebiles(profesor.getMateriasDebiles());
        profesorUpdate.setUsuario(profesor.getUsuario());
        return profesorRepository.save(profesorUpdate);
    }

    @Override
    public String deleteProfesor(long id) {
        Profesor profesorDelete = getProfesor(id);
        profesorRepository.delete(profesorDelete);
        return "Se elimino el profesor con id " + id;
    }

}
