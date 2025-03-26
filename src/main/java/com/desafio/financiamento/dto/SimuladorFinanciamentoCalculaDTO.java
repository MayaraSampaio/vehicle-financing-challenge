package com.desafio.financiamento.dto;

import com.desafio.financiamento.entities.Enums.TipoFinanciamento;

public class SimuladorFinanciamentoCalculaDTO {

    private TipoFinanciamento tipoFinanciamento;
    private Integer numeroMensalidades;
    private Double valorViatura;
    private Double valorPrestacao;

    public SimuladorFinanciamentoCalculaDTO(TipoFinanciamento tipoFinanciamento, Integer numeroDeMensalidades, Double valorViatura, Double valorPrestacao) {
        this.tipoFinanciamento = tipoFinanciamento;
        this.numeroMensalidades = numeroDeMensalidades;
        this.valorViatura = valorViatura;
        this.valorPrestacao = valorPrestacao;
    }

    public void setValorPrestacao(Double valorPrestacao) {
        this.valorPrestacao = valorPrestacao;
    }

    public TipoFinanciamento getTipoFinanciamento() {
        return tipoFinanciamento;
    }

    public Integer getNumeroMensalidades() {
        return numeroMensalidades;
    }

    public Double getValorViatura() {
        return valorViatura;
    }

    public Double getValorPrestacao() {
        return valorPrestacao;
    }
}
