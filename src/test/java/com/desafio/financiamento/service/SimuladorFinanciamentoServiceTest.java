package com.desafio.financiamento.service;

import com.desafio.financiamento.service.exceptions.InvalidVehicleValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static com.desafio.financiamento.entities.Enums.TipoFinanciamento.EXTERNO;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SimuladorFinanciamentoServiceTest {

    @InjectMocks
    private SimuladorFinanciamentoService service;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "fatorInterno", 1.04);
        ReflectionTestUtils.setField(service, "fatorExterno", 1.065);
    }

    @Test
    @DisplayName("Deve calcular financiamento externo usando Mockito")
    void deveCalcularFinanciamentoExterno() {
        Double valorViatura = 50000.0;
        Integer numeroMensalidades = 24;

        Double resultado = service.calculaFinanciamento(EXTERNO, numeroMensalidades, valorViatura);

        assertNotNull(resultado);
        assertEquals(2218.75, resultado, 0.01);

    }

    @Test
    @DisplayName("Deve lançar exceção para valor de viatura inválido")
    void deveLancarExcecaoParaValorNegativo() {

        Double valorViatura = -50000.0;
        Integer numeroMensalidades = 24;

        assertThrows(InvalidVehicleValueException.class, () -> {
            service.calculaFinanciamento(EXTERNO, numeroMensalidades, valorViatura);
        });
    }

}