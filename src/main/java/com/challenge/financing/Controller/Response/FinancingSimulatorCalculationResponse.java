package com.challenge.financing.Controller.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinancingSimulatorCalculationResponse {
        private Double installmentAmount;
        private String message;
}
