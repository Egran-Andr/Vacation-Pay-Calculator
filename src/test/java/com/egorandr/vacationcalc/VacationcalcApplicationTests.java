package com.egorandr.vacationcalc;

import com.egorandr.vacationcalc.models.VacationRequest;
import com.egorandr.vacationcalc.responces.VacationPayResponse;
import com.egorandr.vacationcalc.services.CalculationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationcalcApplicationTests {
    private final CalculationService calculationService = new CalculationService();
    @Test
    @DisplayName("Vacation pay test")
    public void testCalculateVacationPayWithoutDates() {
        VacationRequest request = new VacationRequest(120000, 10, null, null);
        VacationPayResponse result = calculationService.calculateVacationPay(request);
        assertEquals(40956, result.getVacationPay().doubleValue(), String.valueOf(0.01));
    }


    @Test
    @DisplayName("Vacation pay test including holidays and weekends")
    public void testCalculateVacationPayWithDates() { //2 выходных дня + первомай и день победы(6 рабочих дней)
        VacationRequest request = new VacationRequest(120000, 10, LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 10));
        VacationPayResponse result = calculationService.calculateVacationPay(request);
        assertEquals(24573,
                result.getVacationPay().doubleValue(),
                0.01);
    }
}
