package com.api.iberoamericana.colegio.controller.response;

import com.api.iberoamericana.colegio.entity.Rol;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String nombres;
    private RolResponse rol;

}
