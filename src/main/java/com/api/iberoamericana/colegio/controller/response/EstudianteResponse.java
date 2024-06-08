package com.api.iberoamericana.colegio.controller.response;

import com.api.iberoamericana.colegio.entity.Grupo;
import com.api.iberoamericana.colegio.entity.Rol;
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
    private Grupo grupo;
    private RolResponse rol;

}