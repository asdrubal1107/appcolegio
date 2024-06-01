package com.api.iberoamericana.colegio.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoRequest {

    @NotNull
    @Size(min = 1, max = 50)
    private String nombre;

    @Size(max = 200)
    private String descripcion;

}
