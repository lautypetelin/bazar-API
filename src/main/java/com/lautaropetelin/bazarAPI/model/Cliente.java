package com.lautaropetelin.bazarAPI.model;

import com.lautaropetelin.bazarAPI.dto.ClienteActualizadoDTO;
import com.lautaropetelin.bazarAPI.dto.ClienteRegistroDTO;
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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, unique = true, length = 10)
    private String dni;

    public Cliente(ClienteRegistroDTO dto) {
        this.nombre = dto.nombre();
        this.apellido = dto.apellido();
        this.dni = dto.dni();
    }

    public void actualizarDatos(ClienteActualizadoDTO dto) {
        if(dto.nombre() != null && !dto.nombre().isBlank()) this.nombre = dto.nombre();
        if(dto.apellido() != null && !dto.apellido().isBlank()) this.apellido = dto.apellido();
        if(dto.dni() != null && dto.dni().matches("\\d{7,8}")) this.dni = dto.dni();
    }
}