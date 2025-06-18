package com.lautaropetelin.bazarAPI.controller;

import com.lautaropetelin.bazarAPI.dto.ProductoActualizadoDTO;
import com.lautaropetelin.bazarAPI.dto.ProductoListadoDTO;
import com.lautaropetelin.bazarAPI.dto.ProductoRegistroDTO;
import com.lautaropetelin.bazarAPI.dto.ProductoRespuestaDTO;
import com.lautaropetelin.bazarAPI.model.Producto;
import com.lautaropetelin.bazarAPI.service.IProductoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService){
        this.productoService = productoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<ProductoRespuestaDTO> crearProducto(@RequestBody @Valid ProductoRegistroDTO productoRegistroDTO,
                                                  UriComponentsBuilder uriComponentsBuilder){
        Producto producto = productoService.crearProducto(new Producto(productoRegistroDTO));

        ProductoRespuestaDTO productoRespuestaDTO = new ProductoRespuestaDTO(producto);
        URI url = uriComponentsBuilder.path("/productos/{codigo_producto}")
                .buildAndExpand(producto.getCodigoProducto())
                .toUri();

        return ResponseEntity.created(url).body(productoRespuestaDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductoListadoDTO>> traerProductos(){
        return ResponseEntity.ok(
                productoService.traerProductos().stream()
                        .map(p -> new ProductoListadoDTO(p))
                        .toList()
        );
    }

    @GetMapping("/{codigo_producto}")
    public ResponseEntity<ProductoRespuestaDTO> traerProducto(@PathVariable("codigo_producto") Long id){
        Producto producto = productoService.traerProducto(id);
        return ResponseEntity.ok(
                new ProductoRespuestaDTO(producto)
        );
    }

    @PutMapping("/editar/{codigo_producto}")
    public ResponseEntity<ProductoRespuestaDTO> actualizarProducto(@PathVariable("codigo_producto") Long id,
                                                                   @RequestBody @Valid ProductoActualizadoDTO productoActualizadoDTO){
        Producto producto = productoService.traerProducto(id);
        producto.actualizarDatos(productoActualizadoDTO);
        producto = productoService.actualizarProducto(producto);

        ProductoRespuestaDTO productoRespuestaDTO = new ProductoRespuestaDTO(producto);
        return ResponseEntity.ok(productoRespuestaDTO);
    };

    @DeleteMapping("/eliminar/{codigo_producto}")
    public ResponseEntity eliminarProducto(@PathVariable("codigo_producto") Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/falta_stock")
    public ResponseEntity<List<ProductoListadoDTO>> productosConStockMenorA5(){

        List<ProductoListadoDTO> productos = productoService.traerProductosConStockMenorA5();

        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productos);
    }
}