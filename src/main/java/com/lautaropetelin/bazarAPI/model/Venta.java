package com.lautaropetelin.bazarAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoVenta;

    @OneToOne
    @JoinColumn(name = "fk_cliente",
                referencedColumnName = "idCliente")
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDate fechaVenta;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DetalleVenta> listaDeProductos;

    @Column(nullable = false)
    private BigDecimal total;
}