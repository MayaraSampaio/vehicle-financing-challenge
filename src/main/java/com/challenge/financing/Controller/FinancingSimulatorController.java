package com.challenge.financing.Controller;
import com.challenge.financing.Controller.Request.FinancingSimulatorCalculationRequest;
import com.challenge.financing.Controller.Request.FinancingSimulatorSaveRequest;
import com.challenge.financing.Controller.Response.FinancingSimulatorCalculationResponse;
import com.challenge.financing.Service.FinancingSimulatorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("v1/api/simulator")
@Slf4j
public class FinancingSimulatorController {

    @Autowired
    private FinancingSimulatorService service;

    @PostMapping("/calculate")
    public ResponseEntity<FinancingSimulatorCalculationResponse> calculateFinancing(
           @Valid @RequestBody FinancingSimulatorCalculationRequest request) {
        FinancingSimulatorCalculationResponse response = service.processFinancingCalculation(request);
        log.info("Financing simulation calculated successfully: {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveFinancing(@Valid @RequestBody FinancingSimulatorSaveRequest request) {
        service.saveFinancing(request);
        log.info("Financing simulation saved successfully: {}", request);
        return ResponseEntity.ok("Financing simulation saved successfully");
    }
}


