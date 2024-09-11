package com.egorandr.vacationcalc.responces;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VacationPayResponse {

    String message;
    BigDecimal vacationPay;
}

