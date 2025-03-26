package com.desafio.financiamento.service;

import com.desafio.financiamento.dto.SimuladorFinanciamentoGuardaDTO;
import com.desafio.financiamento.entities.Enums.TipoFinanciamento;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

import static com.desafio.financiamento.entities.Enums.TipoFinanciamento.EXTERNO;
import static com.desafio.financiamento.entities.Enums.TipoFinanciamento.INTERNO;

@Service
public class SimuladorFinanciamentoService {

    @Value("${properties.fator.interno:1.04}")
    private Double fatorInterno;
    @Value("${properties.fator.externo:1.065}")
    private Double fatorExterno;

    private static final String CAMINHO_ARQUIVO = "dados_financiamento.txt";

    public Double calculaFinanciamento(TipoFinanciamento tipoFinanciamento, Integer numeroMensalidades, Double valorViatura) {
        if (tipoFinanciamento == EXTERNO && numeroMensalidades >= 12 && numeroMensalidades <= 60) {
            return calculaPrestacaoMensal(fatorExterno,valorViatura,numeroMensalidades);
        }
        if ((tipoFinanciamento == INTERNO && numeroMensalidades >= 12 && numeroMensalidades <= 48)) {
            return calculaPrestacaoMensal(fatorInterno,valorViatura,numeroMensalidades);
        }
        throw new IllegalArgumentException("Parametros de financiamento inválidos");
    }

    public void guardarFinanciamento(SimuladorFinanciamentoGuardaDTO guardaDTO){
        String dados = String.format("nome: %s\nContato: %s\nTipo de Financiamento: %s\nNº de Mensalidades: %d\n" +
                        "Valor da Viatura: %.2f\nPrestação Mensal: %2f\n-------------------------------------------------\n",
                guardaDTO.getCliente().getNome(),guardaDTO.getCliente().getContacto(),guardaDTO.getTipoFinanciamento(),
                guardaDTO.getNumeroMensalidades(),guardaDTO.getValorViatura(),guardaDTO.getValorPrestacao());

        try(FileWriter fileWriter = new FileWriter(CAMINHO_ARQUIVO,true)){
            fileWriter.write(dados);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar financiamento no arquivo: " + CAMINHO_ARQUIVO,e);
        }

    }
    public double calculaPrestacaoMensal(Double fator, Double valorDaViatura, Integer numeroDeMensalidades){
        double valorPrestacao = valorDaViatura * fator/numeroDeMensalidades;

        return Math.round(valorPrestacao * 100.0) / 100.0;
    }
}
