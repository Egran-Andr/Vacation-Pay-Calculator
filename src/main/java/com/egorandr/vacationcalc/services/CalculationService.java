package com.egorandr.vacationcalc.services;

import com.egorandr.vacationcalc.models.VacationRequest;
import com.egorandr.vacationcalc.responces.VacationPayResponse;
import com.egorandr.vacationcalc.utils.HolidayCheck;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class CalculationService {

    private static final double NDFL = 0.13;//процент НДФЛ
    private static final double WORK_DAYS_IN_MONTH = 29.3;//среднее количество рабочих дней за месяц

    public VacationPayResponse calculateVacationPay(VacationRequest request) {
        int vacationDays = request.getVacationDays();

        if (request.getStartDate() != null && request.getEndDate() != null) {
            vacationDays = HolidayCheck.calculateActualVacationDays(request.getStartDate(), request.getEndDate());//Расчет выходных и праздничных дней
        }

        // Рассчитываем дневную зарплату
        BigDecimal dailyPay = BigDecimal.valueOf(request.getAverageSalary())
            .divide(BigDecimal.valueOf(WORK_DAYS_IN_MONTH), 2, RoundingMode.HALF_EVEN);

        // Общий размер отпускных без НДФЛ
        BigDecimal totalPayWithoutNDFL = dailyPay.multiply(BigDecimal.valueOf(vacationDays));

        BigDecimal totalPay;
        if (request.isIncludeNDFL()) {
            // Если включен расчет с НДФЛ, вычитаем налог
            BigDecimal amountNDFL = totalPayWithoutNDFL.multiply(BigDecimal.valueOf(NDFL))
                .setScale(0, RoundingMode.HALF_UP);
            totalPay = totalPayWithoutNDFL.subtract(amountNDFL);

        } else {
            // Если расчет без НДФЛ, оставляем общую сумму без изменений
            totalPay = totalPayWithoutNDFL;
        }

        totalPay = totalPay.setScale(0, RoundingMode.HALF_UP);

        String message = request.isIncludeNDFL() ?
            "Размер отпускных(учитывая НДФЛ):" :
            "Размер отпускных(не учитывая НДФЛ)";

        return VacationPayResponse.builder()
                .message(message)
                .vacationPay(totalPay)
                .build();
    }
}
