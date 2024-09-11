package com.egorandr.vacationcalc.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class HolidayCheck {

    public final static int CURRENT_YEAR = LocalDate.now().getYear();

    //    Список праздников используя забитые значения праздников за 2024 год
    //    1, 2, 3, 4, 5, 6 и 8 января — Новогодние каникулы;
    //    7 января — Рождество Христово;
    //    23 февраля — День защитника Отечества;
    //    8 марта — Международный женский день;
    //    1 мая — Праздник Весны и Труда;
    //    9 мая — День Победы;
    //    12 июня — День России;
    //    4 ноября — День народного единства.

    private static final List<LocalDate> HOLIDAYS = List.of(
                LocalDate.of(CURRENT_YEAR, 1, 1), // Новый год
                LocalDate.of(CURRENT_YEAR, 1, 2),
                LocalDate.of(CURRENT_YEAR, 1, 3),
                LocalDate.of(CURRENT_YEAR, 1, 4),
                LocalDate.of(CURRENT_YEAR, 1, 5),
                LocalDate.of(CURRENT_YEAR, 1, 6),
                LocalDate.of(CURRENT_YEAR, 1, 8),
                LocalDate.of(CURRENT_YEAR, 1, 7), //Рождество
                LocalDate.of(CURRENT_YEAR, 2, 23), // День защитника отечества
                LocalDate.of(CURRENT_YEAR, 3, 8), // Международный женский день
                LocalDate.of(CURRENT_YEAR, 5, 1), // Первомай
                LocalDate.of(CURRENT_YEAR, 5, 9), // День победы
                LocalDate.of(CURRENT_YEAR, 6, 12), // День России
                LocalDate.of(CURRENT_YEAR, 11, 4) // День народного единства
    );

    public static int calculateActualVacationDays(LocalDate startDate, LocalDate endDate) {
        int actualDays = 0;
        while (!startDate.isAfter(endDate)) {
            if (!isWeekend(startDate) && !isHoliday(startDate)) {
                actualDays++;
            }
            startDate = startDate.plusDays(1);
        }
        return actualDays;
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    private static boolean isHoliday(LocalDate date) {
        return HOLIDAYS.contains(date);
    }
}
