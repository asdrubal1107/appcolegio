package com.api.iberoamericana.colegio.service;

import com.api.iberoamericana.colegio.entity.Rol;
import com.api.iberoamericana.colegio.exception.NotFoundException;
import com.api.iberoamericana.colegio.repository.RolRepository;
import com.api.iberoamericana.colegio.service.contract.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolService implements IRolService {

    private final RolRepository rolRepository;

    @Override
    public List<Rol> getRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol getRol(long id) {
        return rolRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(HttpStatus.NOT_FOUND,
                                "No se encontro el rol con id " + id));
    }

    @Override
    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol updateRol(Rol rol, long id){
        Rol rolUpdate = getRol(id);
        rolUpdate.setNombre(rol.getNombre());
        return rolRepository.save(rolUpdate);
    }

    @Override
    public String deleteRol(long id) {
        Rol rol = getRol(id);
        rolRepository.delete(rol);
        return "Se elimino el rol con id " + id;
    }
}
