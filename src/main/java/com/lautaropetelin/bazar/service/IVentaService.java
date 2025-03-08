package com.lautaropetelin.bazar.service;

import com.lautaropetelin.bazar.dto.MayorVentaDTO;
import com.lautaropetelin.bazar.dto.VentaActualizadaDTO;
import com.lautaropetelin.bazar.dto.VentaRegistroDTO;
import com.lautaropetelin.bazar.dto.VentasResumenDTO;
import com.lautaropetelin.bazar.model.Venta;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    Venta crearVenta(VentaRegistroDTO ventaRegistroDTO);

    List<Venta> traerVentas();

    Venta traerVenta(Long id);

    boolean existeLaVenta(Long id);

    Venta actualizarVenta(Long id, VentaActualizadaDTO ventaActualizadaDTO);

    void eliminarVenta(Long id);

    VentasResumenDTO obtenerResumenVentasPorFecha(LocalDate fechaVenta);

    MayorVentaDTO obtenerMayorVenta();
}