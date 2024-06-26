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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfesor;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "profesor")
    @JsonManagedReference
    private List<MateriasProfesor> materiasProfesor;

}
