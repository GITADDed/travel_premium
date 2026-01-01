package org.javaguru.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class DateTimeService {
    private LocalDate convertToLocalDate(Date dateToConvert) {
        return LocalDate.ofInstant(dateToConvert.toInstant(),
                ZoneId.systemDefault());
    }

    public BigDecimal calculateDaysBetween(Date fromD, Date toD) {
        LocalDate from = convertToLocalDate(fromD);
        LocalDate to = convertToLocalDate(toD);

        return BigDecimal.valueOf(ChronoUnit.DAYS.between(from, to));
    }
}
