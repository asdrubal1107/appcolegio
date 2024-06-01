package com.api.iberoamericana.colegio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstudiante;

    @Column(length = 100)
    private String nombreAcudiente;

    @Column(length = 20)
    private String telefonoAcudiente;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean estadoMatricula;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "estudiante")
    private List<CursosAsignados> cursosAsignados;

}
