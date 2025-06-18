package com.lautaropetelin.bazarAPI.repository;

import com.lautaropetelin.bazarAPI.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IVentaRepository extends JpaRepository<Venta, Long> {

    @Query("SELECT v FROM Venta v WHERE v.fechaVenta = :fechaVenta")
    List<Venta> obtenerResumenVentasPorFecha(LocalDate fechaVenta);

    @Query("SELECT v FROM Venta v ORDER BY v.total DESC LIMIT 1")
    Venta obtenerMayorVenta();
}