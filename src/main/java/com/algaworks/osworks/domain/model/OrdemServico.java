package com.algaworks.osworks.domain.model;

import com.algaworks.osworks.api.model.Comentario;
import com.algaworks.osworks.domain.exceptions.DomainException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    private String descricao;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private StatusOrderServico status;

    private OffsetDateTime dataAbertura;

    private OffsetDateTime dataFechamento;

    @OneToMany(mappedBy = "ordemServico")
    private List<Comentario> comentarios = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public StatusOrderServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrderServico status) {
        this.status = status;
    }

    public OffsetDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(OffsetDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public OffsetDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(OffsetDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemServico that = (OrdemServico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean canFinished() {
        return StatusOrderServico.ABERTA.equals(getStatus());
    }

    public boolean notCanFinished() {
        return !canFinished();
    }

    public void finalizar() {
        if (notCanFinished()) {
            throw new DomainException("Ordem não pode ser finalizada");
        }

        setStatus(StatusOrderServico.FINALIZADA);
        setDataFechamento(OffsetDateTime.now());
    }
}
