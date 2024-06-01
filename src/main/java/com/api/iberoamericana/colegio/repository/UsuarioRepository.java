package com.api.iberoamericana.colegio.repository;

import com.api.iberoamericana.colegio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository
        extends JpaRepository<Usuario, Long> {
}
