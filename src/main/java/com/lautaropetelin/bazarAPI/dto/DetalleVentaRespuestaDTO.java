package com.lautaropetelin.bazarAPI.dto;

import com.lautaropetelin.bazarAPI.model.DetalleVenta;

import java.math.BigDecimal;

public record DetalleVentaRespuestaDTO(

        String producto,
        Integer cantidad,
        BigDecimal precioUnitario
) {
    public DetalleVentaRespuestaDTO(DetalleVenta detalle) {
        this(
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getProducto().getCosto()
        );
    }
}
