package com.challenge.financing.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinancingSimulatorCalculationRequest {
    private String financingType;
    private Integer numberOfInstallments;
    private Double vehicleValue;
}

