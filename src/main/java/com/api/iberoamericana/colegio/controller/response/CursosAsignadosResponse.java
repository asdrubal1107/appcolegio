package com.api.iberoamericana.colegio.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursosAsignadosResponse {

    private Long idCursoAsignado;
    private String fechaAsignacion;
    private Double promedio;
    private String curso;
    private String estudiante;
    private String profesor;

}
