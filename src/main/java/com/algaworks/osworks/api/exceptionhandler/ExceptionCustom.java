package com.algaworks.osworks.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionCustom {
    private Integer status;
    private OffsetDateTime localDateTime;
    private String titulo;
    private List<Campo> campos;

    public static class Campo {
        private String nome;
        private String mensagem;

        public Campo(String nome, String mensagem) {
            this.nome = nome;
            this.mensagem = mensagem;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(OffsetDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }
}
