package com.desafio.financiamento.dto;
import com.desafio.financiamento.entities.Enums.TipoFinanciamento;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SimuladorFinanciamentoGuardaDTO {
    private TipoFinanciamento tipoFinanciamento;
    private Integer numeroMensalidades;
    private Double valorViatura;
    private Double valorPrestacao;
    @Setter
    private ClienteDTO cliente;
}
