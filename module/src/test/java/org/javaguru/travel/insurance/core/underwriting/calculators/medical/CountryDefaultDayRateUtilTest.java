package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.javaguru.travel.insurance.core.repository.CountryDefaultDayRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDefaultDayRateUtilTest {

    @Mock
    CountryDefaultDayRateRepository countryDefaultDayRateRepository;

    @InjectMocks
    CountryDefaultDayRateUtil countryDefaultDayRateUtil;

    @Test
    void shouldReturnCountryDefaultDayRateRepository() {
        when(countryDefaultDayRateRepository.findByCountry(Mockito.anyString())).thenReturn(Optional.of(new CountryDefaultDayRate(new BigDecimal("1.00"))));
        BigDecimal coef = countryDefaultDayRateUtil.getDayRateByCountry("JAPAN");

        assertEquals(new BigDecimal("1.00"), coef);
    }
}