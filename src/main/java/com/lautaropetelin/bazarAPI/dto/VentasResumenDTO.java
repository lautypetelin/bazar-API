package com.lautaropetelin.bazarAPI.dto;

import com.lautaropetelin.bazarAPI.model.Venta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record VentasResumenDTO(

        LocalDate fechaVenta,
        BigDecimal montoTotal,
        Long cantidadVentas
) {
    public VentasResumenDTO(LocalDate fechaVenta, List<Venta> ventas) {
        this(
                fechaVenta,
                ventas.stream()
                        .map(Venta::getTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add), // Sumatoria del monto total
                (long) ventas.size() // Cantidad total de ventas
        );
    }
}