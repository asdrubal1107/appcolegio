package com.api.iberoamericana.colegio.repository;

import com.api.iberoamericana.colegio.entity.CursosAsignados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosAsignadosRepository
        extends JpaRepository<CursosAsignados, Long> {
}
