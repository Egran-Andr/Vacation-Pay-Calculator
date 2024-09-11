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
    private boolean includeNDFL= false;// Учитывать ли НДФЛ в расчете (по умолчанию false)

    // Полный конструктор
    public VacationRequest(double averageSalary, int vacationDays, LocalDate startDate, LocalDate endDate, boolean includeNDFL) {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
        this.startDate = startDate;
        this.endDate = endDate;
        this.includeNDFL = includeNDFL;
    }

    // Конструктор с обязательными параметрами
    public VacationRequest(double averageSalary, int vacationDays) {
        this(averageSalary, vacationDays, null, null, false);
    }

    // Конструктор с обязательными параметрами и необязательными датами отпуска
    public VacationRequest(double averageSalary, int vacationDays, LocalDate startDate, LocalDate endDate) {
        this(averageSalary, vacationDays, startDate, endDate, false);
    }
}
