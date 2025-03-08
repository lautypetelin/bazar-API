package com.lautaropetelin.bazar.dto;

import com.lautaropetelin.bazar.model.Venta;

import java.math.BigDecimal;

public record MayorVentaDTO(

        Long codigoVenta,
        String cliente,
        Integer cantidadProductos,
        BigDecimal total
) {
    public MayorVentaDTO(Venta v) {
        this(
                v.getCodigoVenta(),
                v.getCliente().getNombre() + " " + v.getCliente().getApellido(),
                v.getListaDeProductos().stream()
                        .mapToInt(p -> p.getCantidad())
                        .sum(),
                v.getTotal()
        );
    }
}