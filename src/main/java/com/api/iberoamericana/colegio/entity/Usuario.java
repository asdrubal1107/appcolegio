package com.api.iberoamericana.colegio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(length = 20, nullable = false, unique = true)
    private String documento;

    @Column(length = 50)
    private String nombres;

    @Column(length = 50)
    private String apellidos;

    @Column
    private Date fechaNacimiento;

    @Column(length = 100)
    private String direccion;

    @Column(length = 20)
    private String celular;

    @Column(length = 100)
    private String correoElectronico;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private List<Estudiante> estudiantes;

    @OneToMany(mappedBy = "usuario")
    private List<Profesor> profesores;



}
