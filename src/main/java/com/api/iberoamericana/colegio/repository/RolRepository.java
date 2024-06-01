package com.api.iberoamericana.colegio.repository;

import com.api.iberoamericana.colegio.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository
        extends JpaRepository<Rol, Long> {
}
