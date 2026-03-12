package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.repository.CountryDefaultDayRateRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
class CountryDefaultDayRateUtil {
    private final CountryDefaultDayRateRepository countryDefaultDayRatesRepository;

    public BigDecimal getDayRateByCountry(String country) {
        return countryDefaultDayRatesRepository.findByCountry(country).orElseThrow().getDayRate();
    }
}
