package com.lautaropetelin.bazarAPI.service;

import com.lautaropetelin.bazarAPI.dto.ProductoListadoDTO;
import com.lautaropetelin.bazarAPI.model.Producto;

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