package com.challenge.financing.Service;

import com.challenge.financing.Service.Enum.FinancingType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

class FinancingSimulatorServiceTest {

    @Nested
    @DisplayName("Financing Calculation Tests")
    class FinancingCalculationTests {
        private final FinancingSimulatorService service = new FinancingSimulatorServiceBuilder()
                .withInternalFinancingFactor(1.04)
                .withExternalFinancingFactor(1.065)
                .build();

        @ParameterizedTest
        @DisplayName("Calculate Financing for Different Scenarios")
        @CsvSource({
                "INTERNAL, 24, 50000.0, 2166.67",
                "EXTERNAL, 36, 75000.0, 2218.75"
        })
        void testCalculateFinancing(FinancingType type, int months, double vehicleValue, double expectedResult) {
            double result = service.calculateFinancing(type, months, vehicleValue);
            assertEquals(expectedResult, result, 0.01,
                    () -> "Financing calculation failed for " + type + " with " + months + " months and vehicle value " + vehicleValue);
        }
    }
    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {
        private final FinancingSimulatorService service = new FinancingSimulatorService();

        @Test
        @DisplayName("Should throw exception for negative vehicle value")
        void testValidateVehicleValueInvalid() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> service.calculateFinancing(FinancingType.INTERNAL, 24, -5000.0),
                    "Expected an IllegalArgumentException for negative vehicle value"
            );

            assertEquals(
                    "The vehicle value must be greater than zero.",
                    exception.getMessage(),
                    "Error message does not match expected"
            );
        }
    }
    static class FinancingSimulatorServiceBuilder {
        private final FinancingSimulatorService service = new FinancingSimulatorService();

        public FinancingSimulatorServiceBuilder withInternalFinancingFactor(double factor) {
            try {
                Field field = FinancingSimulatorService.class.getDeclaredField("internalFinancingAdjustmentFactor");
                field.setAccessible(true);
                field.set(service, factor);
            } catch (Exception e) {
                throw new RuntimeException("Failed to set internal financing factor", e);
            }
            return this;
        }
        public FinancingSimulatorServiceBuilder withExternalFinancingFactor(double factor) {
            try {
                Field field = FinancingSimulatorService.class.getDeclaredField("externalFinancingAdjustmentFactor");
                field.setAccessible(true);
                field.set(service, factor);
            } catch (Exception e) {
                throw new RuntimeException("Failed to set external financing factor", e);
            }
            return this;
        }

        public FinancingSimulatorService build() {
            return service;
        }
    }
}