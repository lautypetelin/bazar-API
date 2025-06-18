package com.lautaropetelin.bazarAPI.repository;

import com.lautaropetelin.bazarAPI.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByDni(String dni);
}