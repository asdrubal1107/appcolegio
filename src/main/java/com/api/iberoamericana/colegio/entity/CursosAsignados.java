package com.api.iberoamericana.colegio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cursos_asignados")
public class CursosAsignados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCursoAsignado;

    @Column(nullable = false)
    private String fechaAsignacion;

    @Column
    private Double promedio;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    @JsonBackReference
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false)
    @JsonBackReference
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    @JsonBackReference
    private Curso curso;

    @OneToMany(mappedBy = "cursoAsignado", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Nota> notas;

}
