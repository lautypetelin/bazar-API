package com.lautaropetelin.bazarAPI.dto;

import jakarta.validation.constraints.Pattern;

public record ClienteActualizadoDTO(

        String nombre,
        String apellido,

        @Pattern(regexp = "\\d{7,8}", message = "El DNI debe contener entre 7 y 8 dígitos numéricos")
        String dni
) {
}