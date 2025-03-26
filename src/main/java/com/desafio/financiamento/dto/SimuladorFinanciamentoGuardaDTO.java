package com.desafio.financiamento.dto;

import com.desafio.financiamento.entities.Enums.TipoFinanciamento;

public class SimuladorFinanciamentoGuardaDTO {
    private TipoFinanciamento tipoFinanciamento;
    private Integer numeroMensalidades;
    private Double valorViatura;
    private Double valorPrestacao;
    private ClienteDTO cliente;

    public SimuladorFinanciamentoGuardaDTO(TipoFinanciamento tipoFinanciamento, Double valorViatura, Integer numeroMensalidades, Double valorPrestacao, ClienteDTO cliente) {
        this.tipoFinanciamento = tipoFinanciamento;
        this.valorViatura = valorViatura;
        this.numeroMensalidades = numeroMensalidades;
        this.valorPrestacao = valorPrestacao;
        this.cliente = cliente;
    }

    public TipoFinanciamento getTipoFinanciamento() {
        return tipoFinanciamento;
    }

    public Double getValorViatura() {
        return valorViatura;
    }

    public Integer getNumeroMensalidades() {
        return numeroMensalidades;
    }

    public Double getValorPrestacao() {
        return valorPrestacao;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }
}
