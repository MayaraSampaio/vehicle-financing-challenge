package com.challenge.financing.Service;
import com.challenge.financing.Request.FinancingSimulatorCalculationRequest;
import com.challenge.financing.Request.FinancingSimulatorSaveRequest;
import com.challenge.financing.Entity.Enums.FinancingType;
import com.challenge.financing.Response.FinancingSimulatorCalculationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FinancingSimulatorService {

    // Adjustment factors applied to the financing calculation
    // These values can be configured in the application.properties

    @Value("${properties.factor.internal:1.04}") // Adjustment factor for internal financing (default: 1.04)
    private Double internalFinancingAdjustmentFactor;

    @Value("${properties.factor.external:1.065}") // Adjustment factor for external financing (default: 1.065)
    private Double externalFinancingAdjustmentFactor;



    private static final String FILE_PATH = "simulations_data/financing_data.txt";

    public Double calculateFinancing(FinancingType financingType, Integer numberOfInstallments, Double vehicleValue) {
        validateVehicleValue(vehicleValue);
        validateFinancingParameters(financingType, numberOfInstallments);

        Double factor = financingType == FinancingType.EXTERNAL ? externalFinancingAdjustmentFactor : internalFinancingAdjustmentFactor;
        return calculateMonthlyInstallment(factor, vehicleValue, numberOfInstallments);
    }

    private void validateVehicleValue(Double vehicleValue) {
        if (vehicleValue == null || vehicleValue <= 0) {
            throw new IllegalArgumentException("The vehicle value must be greater than zero.");
        }
    }

    private void validateFinancingParameters(FinancingType financingType, Integer numberOfInstallments) {
        boolean validParameters = (financingType == FinancingType.EXTERNAL && numberOfInstallments >= 12 && numberOfInstallments <= 60 && numberOfInstallments % 12 == 0) ||
                (financingType == FinancingType.INTERNAL && numberOfInstallments >= 12 && numberOfInstallments <= 48 && numberOfInstallments % 12 == 0);

        if (!validParameters) {
            throw new IllegalArgumentException("Invalid financing parameters");
        }
    }

    public double calculateMonthlyInstallment(Double factor, Double vehicleValue, Integer numberOfInstallments) {
        double monthlyInstallment = vehicleValue * factor / numberOfInstallments;
        return Math.round(monthlyInstallment * 100.0) / 100.0;
    }

    public void saveFinancing(FinancingSimulatorSaveRequest request) {
        String data = String.format("Name: %s\nContact: %s\nFinancing Type: %s\nNumber of Installments: %d\n" +
                        "Vehicle Value: %.2f\nMonthly Installment: %.2f\n-------------------------------------------------\n",
                request.getClient().getName(),
                request.getClient().getContact(),
                request.getFinancingType(),
                request.getNumberOfInstallments(),
                request.getVehicleValue(),
                request.getInstallmentAmount());

        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save the financing", e);
        }
    }

    public FinancingSimulatorCalculationResponse processFinancingCalculation(FinancingSimulatorCalculationRequest request) {
        FinancingType type = FinancingType.valueOf(request.getFinancingType());
        Double monthlyInstallment = calculateFinancing(type, request.getNumberOfInstallments(), request.getVehicleValue());

        return new FinancingSimulatorCalculationResponse(monthlyInstallment, "Financing simulation successfully calculated");
    }
}
