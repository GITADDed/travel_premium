package org.javaguru.travel.insurance.core.underwriting.calculators;

import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.javaguru.travel.insurance.core.repository.CountryDefaultDayRateRepository;
import org.javaguru.travel.insurance.core.utils.DateTimeService;
import org.javaguru.travel.insurance.dto.Risk;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {
    private final String timeFromStr = "2025-10-16T00:00:00";
    private final String timeToStr = "2025-10-20T00:00:00";
    private final ZoneId zoneId = ZoneId.systemDefault();

    @Mock
    DateTimeService dateTimeService;

    @Mock
    CountryDefaultDayRateRepository countryDefaultDayRatesRepository;

    @InjectMocks
    TravelMedicalRiskPremiumCalculator calculator;

    @Test
    void shouldReturnPremium() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("", "",
                dateFrom, dateTo, Set.of("TRAVEL_MEDICAL"), "JAPAN");

        Mockito.when(dateTimeService.calculateDaysBetween(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);
        Mockito.when(countryDefaultDayRatesRepository.findByCountry(Mockito.any())).thenReturn(Optional.of(new CountryDefaultDayRate(new BigDecimal("3.50"))));

        Risk risk = calculator.calculatePremium(request);

        assertEquals("TRAVEL_MEDICAL", risk.riskIc());
        assertEquals(new BigDecimal("3.50"), risk.premium());
    }

    @Test
    void shouldReturnZeroPremiumWhenNoHaveRisks() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("", "",
                dateFrom, dateTo, Set.of(""), "JAPAN");

        Risk risk = calculator.calculatePremium(request);

        assertEquals("TRAVEL_MEDICAL", risk.riskIc());
        assertEquals(BigDecimal.ZERO, risk.premium());
    }


}