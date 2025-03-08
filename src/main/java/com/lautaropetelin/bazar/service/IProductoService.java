package com.lautaropetelin.bazar.service;

import com.lautaropetelin.bazar.dto.ProductoListadoDTO;
import com.lautaropetelin.bazar.model.Producto;

import java.util.List;

public interface IProductoService {

    Producto crearProducto(Producto producto);

    List<Producto> traerProductos();

    Producto traerProducto(Long id);

    boolean existeElProducto(Long id);

    Producto actualizarProducto(Producto producto);

    void eliminarProducto(Long id);

    List<ProductoListadoDTO> traerProductosConStockMenorA5();
}