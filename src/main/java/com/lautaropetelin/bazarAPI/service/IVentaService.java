package com.lautaropetelin.bazarAPI.service;

import com.lautaropetelin.bazarAPI.dto.MayorVentaDTO;
import com.lautaropetelin.bazarAPI.dto.VentaActualizadaDTO;
import com.lautaropetelin.bazarAPI.dto.VentaRegistroDTO;
import com.lautaropetelin.bazarAPI.dto.VentasResumenDTO;
import com.lautaropetelin.bazarAPI.model.Venta;

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