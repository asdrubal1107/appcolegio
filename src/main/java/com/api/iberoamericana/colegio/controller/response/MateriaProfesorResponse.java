package com.api.iberoamericana.colegio.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MateriaProfesorResponse {

    private Long id;
    private ProfesorResponse profesor;
    private MateriaResponse materia;

}
