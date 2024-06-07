package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.controller.response.RolResponse;
import com.api.iberoamericana.colegio.entity.Rol;
import com.api.iberoamericana.colegio.exception.NotFoundException;
import com.api.iberoamericana.colegio.repository.RolRepository;
import com.api.iberoamericana.colegio.service.contract.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolService implements IRolService {

    private final RolRepository rolRepository;

    @Override
    public List<RolResponse> getRoles() {
        return rolRepository.findAll()
                .stream()
                .map(this::rolToRolResponse)
                .toList();
    }

    @Override
    public RolResponse getRol(long id) {
        return rolToRolResponse(obtenerRolPorId(id));
    }

    @Override
    public RolResponse createRol(Rol rol) {
        return rolToRolResponse(rolRepository.save(rol));
    }

    @Override
    public RolResponse updateRol(Rol rol, long id){
        Rol rolUpdate = obtenerRolPorId(id);
        rolUpdate.setNombre(rol.getNombre());
        return rolToRolResponse(rolRepository.save(rolUpdate));
    }

    @Override
    public String deleteRol(long id) {
        Rol rol = obtenerRolPorId(id);
        rolRepository.delete(rol);
        return "Se elimino el rol con id " + id;
    }

    private Rol obtenerRolPorId(long id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND,
                        "No se encontro el rol con id " + id));
    }

    private RolResponse rolToRolResponse(Rol rol) {
        return RolResponse.builder()
                .id(rol.getIdRol())
                .nombre(rol.getNombre())
                .build();
    }

}
