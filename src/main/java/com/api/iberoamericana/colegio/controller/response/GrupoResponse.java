package com.api.iberoamericana.colegio.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GrupoResponse {

    private Long id;
    private String nombre;

}
