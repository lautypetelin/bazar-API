package com.lautaropetelin.bazarAPI.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record VentaActualizadaDTO(

        @NotNull(message = "El ID de la venta es obligatoria.")
        Long idCliente,

        @NotNull(message = "La fecha de la venta es obligatoria.")
        LocalDate fechaVenta,

        List<DetalleVentaRegistroDTO> detalles
) {
}