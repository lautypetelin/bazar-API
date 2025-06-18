package com.lautaropetelin.bazarAPI.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetalleVentaRegistroDTO(

        @NotNull(message = "El ID del producto es obligatorio.")
        Long idProducto,

        @Min(value = 1, message = "La cantidad debe ser al menos 1.")
        Integer cantidad
) {
}