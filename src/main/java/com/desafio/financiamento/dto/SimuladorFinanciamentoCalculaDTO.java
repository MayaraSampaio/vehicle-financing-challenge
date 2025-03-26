package com.desafio.financiamento.dto;

import com.desafio.financiamento.entities.Enums.TipoFinanciamento;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimuladorFinanciamentoCalculaDTO {

    private TipoFinanciamento tipoFinanciamento;
    private Integer numeroMensalidades;
    private Double valorViatura;
    @Setter
    private Double valorPrestacao;
}
