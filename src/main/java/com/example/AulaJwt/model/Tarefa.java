

package com.example.AulaJwt.model;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
public class Tarefa {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @NotNull()
    private String titulo;

    @NotNull()
    private String descricao;

    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    private Status status = Status.aFazer;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade = Prioridade.BAIXA;

    private LocalDate dataLimite;

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public Status getStatus() {
        return status;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescrição() {
        return descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }


    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
