package com.example.AulaJwt.model;

public enum Prioridade {

    BAIXA("Baixa"),
    MEDIO("Média"),
    ALTO("Alta");

    private final String descricaoPri;

    Prioridade(String descricaoPri) {
        this.descricaoPri = descricaoPri;
    }

    public String getdescricaoPri() {
        return descricaoPri;
    }

}
