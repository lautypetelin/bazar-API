package com.lautaropetelin.bazarAPI.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductoRegistroDTO(

        @NotBlank(message = "El nombre del producto es obligatorio.")
        String nombre,

        @NotBlank(message = "La marca del producto es obligatoria.")
        String marca,

        @NotNull(message = "El valor del producto no puede ser nulo.")
        @DecimalMin(value = "0.01", message = "El valor del producto debe ser mayor a 0.")
        BigDecimal costo,

        @NotNull(message = "La cantidad del producto no puede ser nula.")
        @Min(value = 1, message = "La cantidad disponible debe ser al menos 1.")
        Integer cantidadDisponible
) {
}