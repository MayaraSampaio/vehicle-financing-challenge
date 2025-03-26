package com.desafio.financiamento.dto;

public class ClienteDTO {
    private String nome;
    private String contacto;

    public ClienteDTO(String nome, String contacto) {
        this.nome = nome;
        this.contacto = contacto;
    }

    public String getNome() {
        return nome;
    }

    public String getContacto() {
        return contacto;
    }
}
