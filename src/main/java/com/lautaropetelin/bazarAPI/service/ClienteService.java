package com.lautaropetelin.bazarAPI.service;

import com.lautaropetelin.bazarAPI.model.Cliente;
import com.lautaropetelin.bazarAPI.repository.IClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public Cliente crearCliente(Cliente cliente) {
        if(clienteRepository.existsByDni(cliente.getDni())){
            throw new IllegalArgumentException("Ya existe un cliente con el DNI " + cliente.getDni());
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> traerClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente traerCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el cliente con ID " + id));
    }

    @Override
    public boolean existeElCliente(Long id) {
        return clienteRepository.existsById(id);
    }

    @Override
    @Transactional
    public Cliente actualizarCliente(Cliente cliente) {
        if (!this.existeElCliente(cliente.getIdCliente())) {
            throw new EntityNotFoundException("No se encontró el cliente con ID " + cliente.getIdCliente());
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void eliminarCliente(Long id) {
        if (!this.existeElCliente(id)) {
            throw new EntityNotFoundException("No se encontró el cliente con ID " + id);
        }
        clienteRepository.deleteById(id);
    }
}