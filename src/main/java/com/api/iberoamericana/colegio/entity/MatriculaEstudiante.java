package com.api.iberoamericana.colegio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "matriculas_estudiante")
public class MatriculaEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatriculaEstudiante;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    @JsonBackReference
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_materia_profesor", nullable = false)
    @JsonBackReference
    private MateriasProfesor materiaProfesor;

    @ManyToOne
    @JoinColumn(name = "id_periodo", nullable = false)
    @JsonBackReference
    private Periodo periodo;

    @OneToMany(mappedBy = "matriculaEstudiante")
    @JsonManagedReference
    private List<Nota> notas;

}
