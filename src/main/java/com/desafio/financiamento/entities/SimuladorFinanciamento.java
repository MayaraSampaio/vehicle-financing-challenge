package com.desafio.financiamento.entities;

import com.desafio.financiamento.entities.Enums.TipoFinanciamento;

public class SimuladorFinanciamento {

    private TipoFinanciamento tipoFinanciamento;
    private Integer numeroDeMensalidades;
    private Double valorDaViatura;
    private Double valorPrestacao;
    private Cliente cliente;

    public SimuladorFinanciamento() {}

    public SimuladorFinanciamento(TipoFinanciamento tipoFianciamento, Integer numeroDeMensalidades, Double valorDaViatura, Cliente cliente,Double valorPrestacao) {
        this.tipoFinanciamento = tipoFianciamento;
        this.numeroDeMensalidades = numeroDeMensalidades;
        this.valorDaViatura = valorDaViatura;
        this.cliente = cliente;
        this.valorPrestacao = valorPrestacao;
    }


    public TipoFinanciamento getTipoFianciamento() {
        return tipoFinanciamento;
    }

    public void setTipoFianciamento(TipoFinanciamento tipoFianciamento) {
        this.tipoFinanciamento = tipoFianciamento;
    }

    public Integer getNumeroDeMensalidades() {
        return numeroDeMensalidades;
    }

    public void setNumeroDeMensalidades(Integer numeroDeMensalidades) {
        this.numeroDeMensalidades = numeroDeMensalidades;
    }

    public Double getValorDaViatura() {
        return valorDaViatura;
    }

    public void setValorDaViatura(Double valorDaViatura) {
        this.valorDaViatura = valorDaViatura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setValorPrestacao(Double valorPrestacao) {
        this.valorPrestacao = valorPrestacao;
    }

    public Double getValorPrestacao() {
        return valorPrestacao;

    }

    public void setCliente(String nome, String contacto) {
    }
}
