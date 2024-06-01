package com.api.iberoamericana.colegio.repository;

import com.api.iberoamericana.colegio.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository
        extends JpaRepository<Nota, Long> {
}
