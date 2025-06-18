package com.lautaropetelin.bazarAPI.dto;

import com.lautaropetelin.bazarAPI.model.Venta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record VentaRespuestaDTO(

        Long codigoVenta,
        String cliente,
        LocalDate fechaVenta,
        BigDecimal total,
        List<DetalleVentaRespuestaDTO> detalles
) {
    public VentaRespuestaDTO(Venta venta) {
        this(
                venta.getCodigoVenta(),
                venta.getCliente().getNombre() + " " + venta.getCliente().getApellido(),
                venta.getFechaVenta(),
                venta.getTotal(),
                venta.getListaDeProductos().stream()
                        .map(DetalleVentaRespuestaDTO::new)
                        .toList()
        );
    }
}
