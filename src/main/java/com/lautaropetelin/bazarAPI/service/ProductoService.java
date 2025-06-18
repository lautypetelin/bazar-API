package com.lautaropetelin.bazarAPI.service;

import com.lautaropetelin.bazarAPI.dto.ProductoListadoDTO;
import com.lautaropetelin.bazarAPI.model.Producto;
import com.lautaropetelin.bazarAPI.repository.IProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    private IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> traerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto traerProducto(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con ID " + id));
    }

    @Override
    public boolean existeElProducto(Long id) {
        return productoRepository.existsById(id);
    }

    @Override
    @Transactional
    public Producto actualizarProducto(Producto producto) {
        if(!this.existeElProducto(producto.getCodigoProducto())){
            throw new EntityNotFoundException("No se encontró el producto con codigo " + producto.getCodigoProducto());
        }
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void eliminarProducto(Long id) {
        if(!this.existeElProducto(id)){
            throw new EntityNotFoundException("No se encontró el producto con ID " + id);
        }
        productoRepository.deleteById(id);
    }

    @Override
    public List<ProductoListadoDTO> traerProductosConStockMenorA5() {
        return productoRepository.findByCantidadDisponibleLessThan(5).stream()
                .map(ProductoListadoDTO::new)
                .toList();
    }
}