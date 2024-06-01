package com.api.iberoamericana.colegio.repository;

import com.api.iberoamericana.colegio.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository
        extends JpaRepository<Profesor, Long> {
}
