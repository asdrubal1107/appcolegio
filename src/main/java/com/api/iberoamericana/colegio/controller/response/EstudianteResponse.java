package com.api.iberoamericana.colegio.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EstudianteResponse {

    private Long id;
    private String nombres;
    private String documento;
    private String correoElectronico;
    private String nombreAcudiente;
    private String grupo;
    private String rol;

}