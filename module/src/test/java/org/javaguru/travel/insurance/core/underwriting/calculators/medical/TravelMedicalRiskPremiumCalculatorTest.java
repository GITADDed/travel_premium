package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.config.AgeCoefficientFeatureProperties;
import org.javaguru.travel.insurance.config.InsuranceLimitCoefficientFeatureProperties;
import org.javaguru.travel.insurance.core.repository.AgeCoefficientRepository;
import org.javaguru.travel.insurance.core.repository.CountryDefaultDayRateRepository;
import org.javaguru.travel.insurance.dto.Risk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import testdto.TravelCalculatePremiumRequestTestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {
    private final String timeFromStr = "2025-10-16T00:00:00";
    private final String timeToStr = "2025-10-20T00:00:00";
    private final ZoneId zoneId = ZoneId.systemDefault();
    private final Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
    private final Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
    private final LocalDate dateOfBirth = LocalDate.now().minusDays(15);

    private TravelCalculatePremiumRequestTestDTO request;

    @Mock
    DayCount dayCount;

    @Mock
    CountryDefaultDayRateUtil countryDefaultDayRate;

    @Mock
    AgeCoefficientUtil ageCoefficientUtil;

    @Mock
    CountryDefaultDayRateRepository countryDefaultDayRatesRepository;

    @Mock
    AgeCoefficientRepository ageCoefficientRepository;

    @Mock
    InsuranceLimitCoefficient insuranceLimitCoefficient;

    @Mock
    AgeCoefficientFeatureProperties ageCoefficientFeatureProperties;

    @Mock
    InsuranceLimitCoefficientFeatureProperties insuranceLimitCoefficientFeatureProperties;

    @InjectMocks
    TravelMedicalRiskPremiumCalculator calculator;

    @BeforeEach
    void setUp() {
        request =  new TravelCalculatePremiumRequestTestDTO("", "", dateFrom, dateTo,  "JAPAN", Set.of(""), dateOfBirth, "LEVEL_10000");
    }

    @Test
    void shouldReturnPremium() {
        request.setSelectedRisks(Set.of("TRAVEL_MEDICAL"));

        Mockito.when(dayCount.calculateDaysBetween(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);
        Mockito.when(countryDefaultDayRate.getDayRateByCountry(Mockito.any())).thenReturn(new BigDecimal("3.50"));
        Mockito.when(ageCoefficientUtil.getCoefficientByBirthDate(Mockito.any())).thenReturn(new BigDecimal("1.00"));
        Mockito.when(insuranceLimitCoefficient.getInsuranceLimitCoefficient(Mockito.any())).thenReturn(new BigDecimal("1.00"));
        Mockito.when(insuranceLimitCoefficientFeatureProperties.getEnabled()).thenReturn(true);
        Mockito.when(ageCoefficientFeatureProperties.getEnabled()).thenReturn(true);

        Risk risk = calculator.calculatePremium(request.toDto());

        assertEquals("TRAVEL_MEDICAL", risk.riskIc());
        assertEquals(new BigDecimal("3.50"), risk.premium());
    }

    @Test
    void shouldReturnZeroPremiumWhenNoHaveRisks() {
        Risk risk = calculator.calculatePremium(request.toDto());

        assertEquals("TRAVEL_MEDICAL", risk.riskIc());
        assertEquals(BigDecimal.ZERO, risk.premium());
    }


}