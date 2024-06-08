package com.api.iberoamericana.colegio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "periodos")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeriodo;

    @Column(nullable = false, length = 45)
    private String nombre;

    @OneToMany(mappedBy = "periodo")
    private List<MatriculaEstudiante> matriculaEstudiantes;


}
