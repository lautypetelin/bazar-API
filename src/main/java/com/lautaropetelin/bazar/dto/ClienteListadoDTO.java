package com.lautaropetelin.bazar.dto;

import com.lautaropetelin.bazar.model.Cliente;
import jakarta.validation.constraints.NotBlank;

public record ClienteListadoDTO(

        Long idCliente,
        String nombre,
        String apellido,
        String dni
) {
    public ClienteListadoDTO(Cliente c) {
        this(
                (c != null) ? c.getIdCliente() : null,
                (c != null) ? c.getNombre() : null,
                (c != null) ? c.getApellido() : null,
                (c != null) ? c.getDni() : null
        );
    }
}