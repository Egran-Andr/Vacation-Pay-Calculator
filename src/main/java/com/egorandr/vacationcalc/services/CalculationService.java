package com.egorandr.vacationcalc.services;

import com.egorandr.vacationcalc.models.VacationRequest;
import com.egorandr.vacationcalc.utils.HolidayCheck;
import org.springframework.stereotype.Service;

import static java.lang.Math.round;


@Service
public class CalculationService {

    private static final double WORK_DAYS_IN_MONTH = 29.3;

    public double calculateVacationPay(VacationRequest request) {
        int vacationDays = request.getVacationDays();

        if (request.getStartDate() != null && request.getEndDate() != null) {
            vacationDays = HolidayCheck.calculateActualVacationDays(request.getStartDate(), request.getEndDate());
        }

        double dailyPay = request.getAverageSalary() / WORK_DAYS_IN_MONTH;
        return round(dailyPay * vacationDays);
    }
}
