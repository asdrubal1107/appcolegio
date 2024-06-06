package com.api.iberoamericana.colegio.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorRequest {

    @NotNull
    @Size(min = 1, max = 100)
    private String materiasFuertes;

    @Size(max = 100)
    private String materiasDebiles;

    @NotNull
    private String idUsuario;

}
