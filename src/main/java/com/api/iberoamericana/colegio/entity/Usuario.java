package com.api.iberoamericana.colegio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(length = 100, nullable = false)
    private String nombres;

    @Column(length = 100, nullable = false, unique = true)
    private String correoElectronico;

    @Column(length = 50, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    @JsonBackReference
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Estudiante> estudiantes;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Profesor> profesores;

}