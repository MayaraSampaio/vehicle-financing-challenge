package com.desafio.financiamento.controllers;
import com.desafio.financiamento.dto.SimuladorFinanciamentoCalculaDTO;
import com.desafio.financiamento.dto.SimuladorFinanciamentoGuardaDTO;
import com.desafio.financiamento.entities.Enums.TipoFinanciamento;
import com.desafio.financiamento.service.SimuladorFinanciamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Slf4j
@CrossOrigin(origins = {"*"})
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
        log.info("Simulação de financiamento calculada com sucesso " + dto);
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/guardar")
    public ResponseEntity<String> guardaFinanciamento(@RequestBody SimuladorFinanciamentoGuardaDTO salvarDto) throws IOException {
        service.guardaFinanciamento(salvarDto);
        log.info("Simulação de financiamento salva com sucesso " + salvarDto);
        return ResponseEntity.ok("Simulação de financiamento salva com sucesso");
    }
}
