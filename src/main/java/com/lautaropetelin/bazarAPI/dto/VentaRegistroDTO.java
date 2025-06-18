package com.lautaropetelin.bazarAPI.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VentaRegistroDTO(

        @NotNull(message = "El ID del cliente es obligatorio.")
        Long idCliente,

        List<DetalleVentaRegistroDTO> detalles
) {
}