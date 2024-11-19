package com.example.AulaJwt.model;

public enum Status {
    aFazer("A Fazer"),
    EmProgresso("Em Progresso"),
    Concluida("Concluída");

    private final String descricaoSta;

    Status(String descricaoSta) {
        this.descricaoSta = descricaoSta;
    }



    public String getDescricaoSta() {
        return descricaoSta;
    }
}
