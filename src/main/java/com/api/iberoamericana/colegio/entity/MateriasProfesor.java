package com.api.iberoamericana.colegio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "materias_profesor")
public class MateriasProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMateriaProfesor;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false)
    @JsonBackReference
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "id_materia", nullable = false)
    @JsonBackReference
    private Materia materia;

    @OneToMany(mappedBy = "materiaProfesor")
    private List<MatriculaEstudiante> matriculaEstudiantes;

}
