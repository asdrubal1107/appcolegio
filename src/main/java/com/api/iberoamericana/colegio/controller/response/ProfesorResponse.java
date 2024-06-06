package com.api.iberoamericana.colegio.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfesorResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private String materiasFuertes;
    private String materiasDebiles;

}