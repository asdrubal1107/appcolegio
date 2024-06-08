package com.api.iberoamericana.colegio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNota;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal nota;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_matricula_estudiante")
    @JsonBackReference
    private MatriculaEstudiante matriculaEstudiante;

}
