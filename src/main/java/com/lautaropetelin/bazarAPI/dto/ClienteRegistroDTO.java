package com.lautaropetelin.bazarAPI.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteRegistroDTO(

        @NotBlank(message = "El nombre del cliente es obligatorio.")
        String nombre,

        @NotBlank(message = "El apellido del cliente es obligatorio.")
        String apellido,

        @NotBlank(message = "El dni del cliente es obligatorio.")
        @Pattern(regexp = "\\d{7,8}", message = "El DNI debe contener entre 7 y 8 dígitos numéricos.")
        String dni
) {
}