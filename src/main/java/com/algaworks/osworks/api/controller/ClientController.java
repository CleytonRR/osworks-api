package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Jose");
        cliente1.setEmail("jose@gmail.com");
        cliente1.setTelefone("99910101010");
        Cliente cliente2 = new Cliente();
        cliente1.setId(2L);
        cliente1.setNome("Maria");
        cliente1.setEmail("Maria@gmail.com");
        cliente1.setTelefone("88810101010");
        return Arrays.asList(cliente1, cliente2);
    }
}
