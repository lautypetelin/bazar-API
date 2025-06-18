package com.lautaropetelin.bazarAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "fk_producto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;
}