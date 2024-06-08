package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.response.MateriaProfesorResponse;
import com.api.iberoamericana.colegio.controller.response.MateriaResponse;
import com.api.iberoamericana.colegio.controller.response.ProfesorResponse;
import com.api.iberoamericana.colegio.entity.MateriasProfesor;
import com.api.iberoamericana.colegio.repository.MateriaProfesorRepository;
import com.api.iberoamericana.colegio.service.contract.IMateriaProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaProfesorService implements IMateriaProfesorService {

    private final MateriaProfesorRepository repository;

    @Override
    public List<MateriaProfesorResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toMateriaProfesorResponse)
                .toList();
    }

    private MateriaProfesorResponse toMateriaProfesorResponse(MateriasProfesor materiasProfesor){
        return MateriaProfesorResponse.builder()
                .id(materiasProfesor.getIdMateriaProfesor())
                .profesor(ProfesorResponse.builder()
                        .id(materiasProfesor.getProfesor().getIdProfesor())
                        .nombres(materiasProfesor.getProfesor().getUsuario().getNombres())
                        .build())
                .materia(MateriaResponse.builder()
                        .id(materiasProfesor.getMateria().getIdMateria())
                        .nombre(materiasProfesor.getMateria().getNombre())
                        .build())
                .build();
    }

}
