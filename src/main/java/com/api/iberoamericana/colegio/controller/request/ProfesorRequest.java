package com.api.iberoamericana.colegio.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfesorRequest {

    @NotNull
    @Size(min = 1, max = 20)
    private String documento;

    @NotNull
    @Size(min = 1, max = 50)
    private String nombres;

    @Size(max = 100)
    @NotNull
    @Email
    private String correoElectronico;

    @NotNull
    @Size(max = 50)
    private String password;

    @NotNull
    private String idRol;

}
