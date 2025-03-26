package com.desafio.financiamento.service;

import com.desafio.financiamento.entities.Enums.TipoFinanciamento;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.desafio.financiamento.entities.Enums.TipoFinanciamento.EXTERNO;
import static com.desafio.financiamento.entities.Enums.TipoFinanciamento.INTERNO;

@Service
public class SimuladorFinanciamentoService {

    @Value("${properties.fator.interno:1.04}")
    private Double fatorInterno;
    @Value("${properties.fator.externo:1.065}")
    private Double fatorExterno;


    public Double calculaFinanciamento(TipoFinanciamento tipoFinanciamento, Integer numeroMensalidades, Double valorViatura) {
        if (tipoFinanciamento == EXTERNO && numeroMensalidades >= 12 && numeroMensalidades <= 60) {
            return calculaPrestacaoMensal(fatorExterno,valorViatura,numeroMensalidades);
        }
        if ((tipoFinanciamento == INTERNO && numeroMensalidades >= 12 && numeroMensalidades <= 48)) {
            return calculaPrestacaoMensal(fatorInterno,valorViatura,numeroMensalidades);
        }
        throw new IllegalArgumentException("Parametros de financiamento inválidos");
    }

    public double calculaPrestacaoMensal(Double fator, Double valorDaViatura, Integer numeroDeMensalidades){
        double valorPrestacao = valorDaViatura * fator/numeroDeMensalidades;

        return Math.round(valorPrestacao * 100.0) / 100.0;
    }
}
