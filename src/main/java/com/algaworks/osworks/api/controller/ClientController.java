package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.service.CrudClienteService;
import com.mysql.cj.xdevapi.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CrudClienteService crudClienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> findOne(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return crudClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizarCliente(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
        if(!clienteRepository.existsById(clienteId)) {
            ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = crudClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        crudClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }
}
