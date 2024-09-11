package com.egorandr.vacationcalc.controller;
import com.egorandr.vacationcalc.models.VacationRequest;
import com.egorandr.vacationcalc.responces.VacationPayResponse;
import com.egorandr.vacationcalc.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

//@Api(tags = {"Vacation pay calculator part"})
@RestController
public class VacationController {

    private final CalculationService calculationService;

    @Autowired
    public VacationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("/calculate")
    public VacationPayResponse calculateVacationPay(
            @RequestParam double averageSalary,
            @RequestParam int vacationDays,
            @RequestParam(required = false) boolean includeNDFL,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        VacationRequest request = new VacationRequest(
                averageSalary,
                vacationDays,
                startDate != null ? LocalDate.parse(startDate) : null,
                endDate != null ? LocalDate.parse(endDate) : null,
                includeNDFL
        );

        return calculationService.calculateVacationPay(request);
    }

}
