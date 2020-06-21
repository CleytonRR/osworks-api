package com.algaworks.osworks.domain.service;

import com.algaworks.osworks.domain.exceptions.DomainException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.model.StatusOrderServico;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class CrudOrderService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico create(OrdemServico ordemServico) {

        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new DomainException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrderServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        return ordemServicoRepository.save(ordemServico);
    }
}
