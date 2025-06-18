package com.lautaropetelin.bazarAPI.model;

import com.lautaropetelin.bazarAPI.dto.ProductoActualizadoDTO;
import com.lautaropetelin.bazarAPI.dto.ProductoRegistroDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoProducto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false)
    private BigDecimal costo;

    @Column(nullable = false)
    private Integer cantidadDisponible;

    public Producto(ProductoRegistroDTO dto) {
        this.nombre = dto.nombre();
        this.marca = dto.marca();
        this.costo = dto.costo();
        this.cantidadDisponible = dto.cantidadDisponible();
    }

    public void actualizarDatos(ProductoActualizadoDTO dto) {
        if(dto.nombre() != null) this.nombre = dto.nombre();
        if(dto.marca() != null) this.marca = dto.marca();
        if(dto.costo() != null) this.costo = dto.costo();
        if(dto.cantidadDisponible() != null) this.cantidadDisponible = dto.cantidadDisponible();
    }
}