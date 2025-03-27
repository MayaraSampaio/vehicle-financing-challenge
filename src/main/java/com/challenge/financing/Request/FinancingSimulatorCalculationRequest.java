package com.challenge.financing.Request;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinancingSimulatorCalculationRequest {
    @NotNull(message = "Financing type is required")
    private String financingType;

    @NotNull(message = "Number of installments is required")
    @Min(value = 12, message = "Number of installments must be at least 12")
    @Max(value = 60, message = "Number of installments must be at most 60")
    private Integer numberOfInstallments;

    @NotNull(message = "Vehicle value is required")
    @Min(value = 1, message = "Vehicle value must be greater than zero")
    private Double vehicleValue;
}

