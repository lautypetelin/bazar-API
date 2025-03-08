package com.lautaropetelin.bazar.service;

import com.lautaropetelin.bazar.model.Cliente;

import java.util.List;

public interface IClienteService {

    Cliente crearCliente(Cliente cliente);

    List<Cliente> traerClientes();

    Cliente traerCliente(Long id);

    boolean existeElCliente(Long id);

    void eliminarCliente(Long id);

    Cliente actualizarCliente(Cliente cliente);
}