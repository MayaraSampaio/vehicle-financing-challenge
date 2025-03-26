package com.desafio.financiamento.entities;

import com.desafio.financiamento.entities.Enums.TipoFinanciamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimuladorFinanciamento {

    private TipoFinanciamento tipoFinanciamento;
    private Integer numeroDeMensalidades;
    private Double valorDaViatura;
    private Double valorPrestacao;
    private Cliente cliente;

}
