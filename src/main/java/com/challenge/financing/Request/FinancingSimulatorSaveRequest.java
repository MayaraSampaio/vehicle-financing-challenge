package com.challenge.financing.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinancingSimulatorSaveRequest {
    private String financingType;
    private Integer numberOfInstallments;
    private Double vehicleValue;
    private Double installmentAmount;
    private ClientRequest client;
}
