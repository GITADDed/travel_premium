package org.javaguru.travel.insurance.core.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeServiceTest {
    private DateTimeService dateTimeService;

    @BeforeEach
    void setUp() {
        dateTimeService = new DateTimeService();
    }

    @Test
    @DisplayName("should calculate days between two dates if difference is positive")
    void shouldCalculateDaysBetweenDaysInCorrectOrder() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700086400000L);
        BigDecimal result = dateTimeService.calculateDaysBetween(from, to);

        assertEquals(0, result.compareTo(BigDecimal.valueOf(1)));
    }

    @Test
    @DisplayName("should return zero if both dates are the same")
    void shouldReturnZeroIfBothDatesAreTheSame() {
        Date date = new Date(1700000000000L);
        assertEquals(0, dateTimeService.calculateDaysBetween(date, date).compareTo(BigDecimal.ZERO));
    }
}
