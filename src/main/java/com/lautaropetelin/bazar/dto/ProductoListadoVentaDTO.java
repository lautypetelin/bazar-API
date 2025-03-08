package com.lautaropetelin.bazar.dto;

import com.lautaropetelin.bazar.model.Producto;

import java.math.BigDecimal;

public record ProductoListadoVentaDTO(

        Long codigoProducto,
        String nombre,
        String marca,
        BigDecimal costo,
        Integer cantidadVendida
) {
    public ProductoListadoVentaDTO(Producto p, Integer cantidadVendida) {
        this(
                p.getCodigoProducto(),
                p.getNombre(),
                p.getMarca(),
                p.getCosto(),
                cantidadVendida
        );
    }
}