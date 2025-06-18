package com.lautaropetelin.bazarAPI.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record ProductoActualizadoDTO(

        String nombre,
        String marca,
        @DecimalMin(value = "0.01", message = "El valor del producto debe ser mayor a 0.")
        BigDecimal costo,
        @Min(value = 1, message = "La cantidad disponible debe ser al menos 1.")
        Integer cantidadDisponible
) {
}