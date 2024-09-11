package com.egorandr.vacationcalc.models;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
public class VacationRequest {
    private double averageSalary;  // Средняя зарплата за 12 месяцев
    private int vacationDays;      // Количество дней отпуска
    private LocalDate startDate;   // Дата начала отпуска
    private LocalDate endDate;     // Дата окончания отпуска

    // Конструктор с обязательными параметрами
    public VacationRequest(double averageSalary, int vacationDays) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
    }

    // Конструктор с обязательными параметрами и необязательными (в паре)
    public VacationRequest(double averageSalary, int vacationDays, LocalDate startDate, LocalDate endDate) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
