package com.lautaropetelin.bazarAPI.controller;

import com.lautaropetelin.bazarAPI.dto.ClienteActualizadoDTO;
import com.lautaropetelin.bazarAPI.dto.ClienteListadoDTO;
import com.lautaropetelin.bazarAPI.dto.ClienteRegistroDTO;
import com.lautaropetelin.bazarAPI.dto.ClienteRespuestaDTO;
import com.lautaropetelin.bazarAPI.model.Cliente;
import com.lautaropetelin.bazarAPI.service.IClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/crear")
    public ResponseEntity<ClienteRespuestaDTO> crearCliente(@RequestBody @Valid ClienteRegistroDTO clienteRegistroDTO,
                                                            UriComponentsBuilder uriComponentsBuilder){
        Cliente cliente = clienteService.crearCliente(new Cliente(clienteRegistroDTO));

        ClienteRespuestaDTO clienteRespuestaDTO = new ClienteRespuestaDTO(cliente);
        URI url = uriComponentsBuilder.path("/clientes/{id_cliente}")
                .buildAndExpand(cliente.getIdCliente())
                .toUri();

        return ResponseEntity.created(url).body(clienteRespuestaDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteListadoDTO>> traerClientes(){
        return ResponseEntity.ok(
                clienteService.traerClientes().stream()
                        .map(c -> new ClienteListadoDTO(c))
                        .toList()
        );
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<ClienteRespuestaDTO> traerCliente(@PathVariable("id_cliente") Long id){
        Cliente cliente = clienteService.traerCliente(id);
        return ResponseEntity.ok(
                new ClienteRespuestaDTO(cliente)
        );
    }

    @PutMapping("/editar/{id_cliente}")
    public ResponseEntity<ClienteRespuestaDTO> actualizarCliente(@PathVariable("id_cliente") Long id,
                                                                 @RequestBody @Valid ClienteActualizadoDTO clienteActualizadoDTO){
        Cliente cliente = clienteService.traerCliente(id);
        cliente.actualizarDatos(clienteActualizadoDTO);
        cliente = clienteService.actualizarCliente(cliente);

        ClienteRespuestaDTO clienteRespuestaDTO = new ClienteRespuestaDTO(cliente);
        return ResponseEntity.ok(clienteRespuestaDTO);
    }

    @DeleteMapping("/eliminar/{id_cliente}")
    public ResponseEntity eliminarCliente(@PathVariable("id_cliente") Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}