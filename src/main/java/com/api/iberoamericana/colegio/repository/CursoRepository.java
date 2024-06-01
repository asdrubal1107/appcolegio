package com.api.iberoamericana.colegio.repository;

import com.api.iberoamericana.colegio.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository
        extends JpaRepository<Curso, Long> {
}
