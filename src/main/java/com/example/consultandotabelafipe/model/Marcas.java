package com.example.consultandotabelafipe.model;


public record Marcas(String codigo,
                     String nome) {
    @Override
    public String toString() {
        return "codigo = " + codigo +
                ", nome = " + nome;
    }
}
