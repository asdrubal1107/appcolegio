package com.api.iberoamericana.colegio.entity;

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
    private Date fechaAsignacion;

    @Column(precision = 3, scale = 2)
    private BigDecimal promedio;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_profesor", nullable = false)
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "cursoAsignado", fetch = FetchType.EAGER)
    private List<Nota> notas;

}
