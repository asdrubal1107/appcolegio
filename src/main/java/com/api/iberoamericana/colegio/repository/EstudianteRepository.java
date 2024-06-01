package com.api.iberoamericana.colegio.repository;

import com.api.iberoamericana.colegio.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository
        extends JpaRepository<Estudiante, Long> {
}
