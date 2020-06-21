package com.algaworks.osworks.domain.service;

import com.algaworks.osworks.api.exceptionhandler.ExceptionCustom;
import com.algaworks.osworks.api.model.Comentario;
import com.algaworks.osworks.domain.exceptions.DomainException;
import com.algaworks.osworks.domain.exceptions.EntityNotFoundException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.model.StatusOrderServico;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.repository.ComentarioRepository;
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

    @Autowired
    private ComentarioRepository comentarioRepository;

    public OrdemServico create(OrdemServico ordemServico) {

        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new DomainException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrderServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());
        return ordemServicoRepository.save(ordemServico);
    }

    public void finalizar(Long ordemServiceId) {
        OrdemServico ordemServico = getOrdemServico(ordemServiceId);
        ordemServico.finalizar();

        ordemServicoRepository.save(ordemServico);
    }

    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = getOrdemServico(ordemServicoId);

        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);
    }

    private OrdemServico getOrdemServico(Long ordemServiceId) {
        return ordemServicoRepository.findById(ordemServiceId)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada."));
    }
}
