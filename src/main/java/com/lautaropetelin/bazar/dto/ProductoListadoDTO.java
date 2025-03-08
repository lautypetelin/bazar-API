package com.lautaropetelin.bazar.dto;

import com.lautaropetelin.bazar.model.Producto;

import java.math.BigDecimal;

public record ProductoListadoDTO(

        Long codigoProducto,
        String nombre,
        String marca,
        BigDecimal costo,
        Integer cantidadDisponible
) {
    public ProductoListadoDTO(Producto p) {
        this(
                p.getCodigoProducto(),
                p.getNombre(),
                p.getMarca(),
                p.getCosto(),
                p.getCantidadDisponible()
        );
    }
}