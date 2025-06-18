package com.lautaropetelin.bazarAPI.dto;

import com.lautaropetelin.bazarAPI.model.Venta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record VentaListadoDTO(

        Long codigoVenta,
        String clienteNombre,
        LocalDate fechaVenta,
        List<DetalleVentaRespuestaDTO> productosVendidos,
        BigDecimal total
) {
    public VentaListadoDTO(Venta venta) {
        this(
                venta.getCodigoVenta(),
                venta.getCliente().getNombre() + " " + venta.getCliente().getApellido(),
                venta.getFechaVenta(),
                venta.getListaDeProductos().stream()
                        .map(DetalleVentaRespuestaDTO::new)
                        .toList(),
                venta.getTotal()
        );
    }
}
