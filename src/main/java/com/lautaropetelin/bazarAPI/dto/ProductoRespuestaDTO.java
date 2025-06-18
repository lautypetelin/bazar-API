package com.lautaropetelin.bazarAPI.dto;

import com.lautaropetelin.bazarAPI.model.Producto;

import java.math.BigDecimal;

public record ProductoRespuestaDTO(

    Long codigoProducto,
    String nombre,
    String marca,
    BigDecimal costo,
    Integer cantidadDisponible
) {
    public ProductoRespuestaDTO(Producto p) {
        this(
                (p != null) ? p.getCodigoProducto() : null,
                (p != null) ? p.getNombre() : null,
                (p != null) ? p.getMarca() : null,
                (p != null) ? p.getCosto() : null,
                (p != null) ? p.getCantidadDisponible() : null
        );
    }
}