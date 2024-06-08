package com.api.iberoamericana.colegio.controller.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UsuarioResponse {

    private Long id;
    private String documento;
    private String nombres;
    private String correoElectronico;
    private String password;
    private String rol;

}
