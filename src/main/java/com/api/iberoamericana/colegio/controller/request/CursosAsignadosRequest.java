package com.api.iberoamericana.colegio.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursosAsignadosRequest {

    private String idEstudiante;
    private String idProfesor;
    private String idCurso;

}
