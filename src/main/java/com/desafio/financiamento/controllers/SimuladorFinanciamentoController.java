package com.desafio.financiamento.controllers;
import com.desafio.financiamento.dto.SimuladorFinanciamentoCalculaDTO;
import com.desafio.financiamento.entities.Enums.TipoFinanciamento;
import com.desafio.financiamento.service.SimuladorFinanciamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/simulacao")
public class SimuladorFinanciamentoController {
    @Autowired
    private SimuladorFinanciamentoService service;

    @PostMapping("/calcular")
    public ResponseEntity<SimuladorFinanciamentoCalculaDTO> calculaFinanciamento(@RequestBody SimuladorFinanciamentoCalculaDTO dto) {
        Double valorPrestacao = service.calculaFinanciamento(
                TipoFinanciamento.valueOf(dto.getTipoFinanciamento().name()),
                dto.getNumeroMensalidades(),dto.getValorViatura());

        dto.setValorPrestacao(valorPrestacao);
        return ResponseEntity.ok(dto);
    }

}
