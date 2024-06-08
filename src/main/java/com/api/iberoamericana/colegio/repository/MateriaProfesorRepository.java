package com.api.iberoamericana.colegio.repository;

import com.api.iberoamericana.colegio.entity.MateriasProfesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaProfesorRepository extends JpaRepository<MateriasProfesor, Long> {
}
