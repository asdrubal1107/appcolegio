package com.api.iberoamericana.colegio.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfesorResponse {

    private Long id;
    private String nombre;
    private String documento;
    private String correoElectronico;

}