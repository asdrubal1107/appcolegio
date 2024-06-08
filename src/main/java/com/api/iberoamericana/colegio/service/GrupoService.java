package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.response.GrupoResponse;
import com.api.iberoamericana.colegio.entity.Grupo;
import com.api.iberoamericana.colegio.repository.GrupoRepository;
import com.api.iberoamericana.colegio.service.contract.IGrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoService implements IGrupoService {

    private final GrupoRepository grupoRepository;

    @Override
    public List<GrupoResponse> getAll() {
        return grupoRepository.findAll()
                .stream()
                .map(this::toGrupoResponse)
                .toList();
    }

    private GrupoResponse toGrupoResponse(Grupo grupo) {
        return GrupoResponse.builder()
                .id(grupo.getIdGrupo())
                .nombre(grupo.getNombre())
                .build();
    }

}
