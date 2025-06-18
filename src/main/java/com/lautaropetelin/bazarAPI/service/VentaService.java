package com.lautaropetelin.bazarAPI.service;

import com.lautaropetelin.bazarAPI.dto.*;
import com.lautaropetelin.bazarAPI.model.Cliente;
import com.lautaropetelin.bazarAPI.model.DetalleVenta;
import com.lautaropetelin.bazarAPI.model.Producto;
import com.lautaropetelin.bazarAPI.model.Venta;
import com.lautaropetelin.bazarAPI.repository.IClienteRepository;
import com.lautaropetelin.bazarAPI.repository.IProductoRepository;
import com.lautaropetelin.bazarAPI.repository.IVentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    private final IClienteRepository clienteRepository;
    private IProductoRepository productoRepository;
    private final IVentaRepository ventaRepository;

    public VentaService(IClienteRepository clienteRepository,
                        IProductoRepository productoRepository,
                        IVentaRepository ventaRepository){
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    @Override
    @Transactional
    public Venta crearVenta(VentaRegistroDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.idCliente())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el cliente con ID " + dto.idCliente()));

        List<DetalleVenta> detalles = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for(DetalleVentaRegistroDTO detalleDTO: dto.detalles()){
            Producto producto = productoRepository.findById(detalleDTO.idProducto())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID " + detalleDTO.idProducto()));

            if(producto.getCantidadDisponible() < detalleDTO.cantidad()){
                throw new IllegalArgumentException("Stock insuficiente para el producto " + producto.getNombre());
            }
            producto.setCantidadDisponible(producto.getCantidadDisponible() - detalleDTO.cantidad());
            productoRepository.save(producto);

            BigDecimal subtotal = producto.getCosto().multiply(BigDecimal.valueOf(detalleDTO.cantidad()));
            total = total.add(subtotal);

            DetalleVenta detalle = new DetalleVenta(null, null, producto, detalleDTO.cantidad());
            detalles.add(detalle);
        }

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFechaVenta(LocalDate.now());
        venta.setTotal(total);

        venta = ventaRepository.save(venta);

        for(DetalleVenta detalle: detalles){
            detalle.setVenta(venta);
        }
        venta.setListaDeProductos(detalles);
        return ventaRepository.save(venta);
    }

    @Override
    public List<Venta> traerVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta traerVenta(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la venta con ID " + id));
    }

    @Override
    public boolean existeLaVenta(Long id) {
        return ventaRepository.existsById(id);
    }

    @Override
    @Transactional
    public Venta actualizarVenta(Long id, VentaActualizadaDTO dto) {

        Venta venta = this.traerVenta(id);

        Cliente cliente = clienteRepository.findById(dto.idCliente())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el cliente con ID " + dto.idCliente()));

        LocalDate fechaVenta = dto.fechaVenta();

        for (DetalleVenta detalle : venta.getListaDeProductos()) {
            Producto producto = detalle.getProducto();
            producto.setCantidadDisponible(producto.getCantidadDisponible() + detalle.getCantidad());
            productoRepository.save(producto);
        }

        List<DetalleVenta> nuevosDetalles = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        
        List<DetalleVentaRegistroDTO> detallesDTO = dto.detalles() != null ? dto.detalles() : mapearDetallesDeVenta(venta);

        for(DetalleVentaRegistroDTO detalleDTO: detallesDTO){
            Producto producto = productoRepository.findById(detalleDTO.idProducto())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID " + detalleDTO.idProducto()));

            if(producto.getCantidadDisponible() < detalleDTO.cantidad()){
                throw new IllegalArgumentException("Stock insuficiente para el producto " + producto.getNombre() + " (ID " + producto.getCodigoProducto() + ")");
            }

            producto.setCantidadDisponible(producto.getCantidadDisponible() - detalleDTO.cantidad());
            productoRepository.save(producto);

            BigDecimal subtotal = producto.getCosto().multiply(BigDecimal.valueOf(detalleDTO.cantidad()));
            total = total.add(subtotal);

            DetalleVenta detalle = new DetalleVenta(null, venta, producto, detalleDTO.cantidad());
            nuevosDetalles.add(detalle);
        }

        venta.setCliente(cliente);
        venta.setFechaVenta(fechaVenta);
        venta.setTotal(total);

        if(!nuevosDetalles.isEmpty()){
            venta.getListaDeProductos().clear();
            venta.getListaDeProductos().addAll(nuevosDetalles);
        }
        return ventaRepository.save(venta);
    }

    private List<DetalleVentaRegistroDTO> mapearDetallesDeVenta(Venta venta) {
        List<DetalleVentaRegistroDTO> detallesDTO = new ArrayList<>();
        for (DetalleVenta detalle : venta.getListaDeProductos()) {
            detallesDTO.add(new DetalleVentaRegistroDTO(
                    detalle.getProducto().getCodigoProducto(),
                    detalle.getCantidad()
            ));
        }
        return detallesDTO;
    }

    @Override
    @Transactional
    public void eliminarVenta(Long id) {
        if(!this.existeLaVenta(id)){
            throw new EntityNotFoundException("No se encontró la venta con ID " + id);
        }
        ventaRepository.deleteById(id);
    }

    @Override
    public VentasResumenDTO obtenerResumenVentasPorFecha(LocalDate fechaVenta) {

        List<Venta> ventas = ventaRepository.obtenerResumenVentasPorFecha(fechaVenta);

        VentasResumenDTO ventasResumenDTO = new VentasResumenDTO(fechaVenta, ventas);

        return ventasResumenDTO;
    }

    @Override
    public MayorVentaDTO obtenerMayorVenta() {
        Venta venta = ventaRepository.obtenerMayorVenta();
        MayorVentaDTO mayorVenta = new MayorVentaDTO(venta);
        return mayorVenta;
    }
}