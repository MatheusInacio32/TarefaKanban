package com.example.AulaJwt.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Senha é obrigatória")
    private String senha;

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Nome é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "Nome é obrigatório") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "Senha é obrigatória") String getSenha() {
        return senha;
    }
}
