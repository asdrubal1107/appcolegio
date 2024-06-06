package com.api.iberoamericana.colegio.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoResponse {

    private Long id;
    private String nombre;
    private String descripcion;

}
