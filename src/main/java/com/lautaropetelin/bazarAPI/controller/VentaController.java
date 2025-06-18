package com.lautaropetelin.bazarAPI.controller;

import com.lautaropetelin.bazarAPI.dto.*;
import com.lautaropetelin.bazarAPI.model.Venta;
import com.lautaropetelin.bazarAPI.service.IVentaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService){
        this.ventaService = ventaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<VentaRespuestaDTO> crearVenta(@RequestBody @Valid VentaRegistroDTO ventaRegistroDTO,
                                                        UriComponentsBuilder uriComponentsBuilder){
        Venta venta = ventaService.crearVenta(ventaRegistroDTO);

        VentaRespuestaDTO ventaRespuestaDTO = new VentaRespuestaDTO(venta);
        URI url = uriComponentsBuilder.path("/ventas/{codigo_venta}")
                .buildAndExpand(venta.getCodigoVenta())
                .toUri();

        return ResponseEntity.created(url).body(ventaRespuestaDTO);
    }

    @GetMapping
    public ResponseEntity<List<VentaListadoDTO>> traerVentas(){
        List<VentaListadoDTO> ventas = ventaService.traerVentas().stream()
                .map(VentaListadoDTO::new)
                .toList();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{codigo_venta}")
    public ResponseEntity<VentaRespuestaDTO> traerVenta(@PathVariable("codigo_venta") Long id){
        Venta venta = ventaService.traerVenta(id);
        return ResponseEntity.ok(
                new VentaRespuestaDTO(venta)
        );
    }

    @PutMapping("/editar/{codigo_venta}")
    public ResponseEntity<VentaRespuestaDTO> actualizarVenta(@PathVariable("codigo_venta") Long id,
                                                             @RequestBody @Valid VentaActualizadaDTO ventaActualizadaDTO){
        Venta venta = ventaService.actualizarVenta(id, ventaActualizadaDTO);
        VentaRespuestaDTO ventaRespuestaDTO = new VentaRespuestaDTO(venta);

        return ResponseEntity.ok(ventaRespuestaDTO);
    }

    @DeleteMapping("/eliminar/{codigo_venta}")
    public ResponseEntity eliminarVenta(@PathVariable("codigo_venta") Long id){
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/productos/{codigo_venta}")
    public ResponseEntity<List<ProductoListadoVentaDTO>> obtenerListaDeProductosPorId(@PathVariable("codigo_venta") Long id){
        Venta venta = ventaService.traerVenta(id);

        List<ProductoListadoVentaDTO> productos = venta.getListaDeProductos().stream()
                .map(detalleVenta -> new ProductoListadoVentaDTO(detalleVenta.getProducto(), detalleVenta.getCantidad()))
                .toList();

        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/fecha/{fecha_venta}")
    public ResponseEntity<VentasResumenDTO> obtenerResumenVentasPorFecha(@PathVariable("fecha_venta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaVenta){

        VentasResumenDTO resumen = ventaService.obtenerResumenVentasPorFecha(fechaVenta);
        return ResponseEntity.ok(resumen);
    }

    @GetMapping("/mayor_venta")
    public ResponseEntity<MayorVentaDTO> obtenerMayorVenta(){

        MayorVentaDTO mayorVenta = ventaService.obtenerMayorVenta();
        return ResponseEntity.ok(mayorVenta);
    }
}