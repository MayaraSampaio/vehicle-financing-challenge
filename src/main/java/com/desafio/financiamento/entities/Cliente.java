package com.desafio.financiamento.entities;

public class Cliente {
    private String nome;
    private String contacto;

    public Cliente(){}

    public Cliente(String contacto, String nome) {
        this.contacto = contacto;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
}
