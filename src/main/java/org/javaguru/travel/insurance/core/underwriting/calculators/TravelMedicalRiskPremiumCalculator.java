package org.javaguru.travel.insurance.core.underwriting.calculators;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.repository.CountryDefaultDayRateRepository;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.javaguru.travel.insurance.core.utils.DateTimeService;
import org.javaguru.travel.insurance.dto.Risk;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final DateTimeService dateTimeService;
    private final CountryDefaultDayRateRepository countryDefaultDayRatesRepository;

    @Override
    public Risk calculatePremium(TravelCalculatePremiumRequest request) {
        if (request.selectedRisks().contains(getRiskIc())) {
            BigDecimal premium = dateTimeService
                    .calculateDaysBetween(request.agreementDateFrom(), request.agreementDateTo())
                    .multiply(countryDefaultDayRatesRepository.findByCountry(request.country()).orElseThrow().getDayRate());
            return new Risk(getRiskIc(), premium.setScale(2, RoundingMode.HALF_UP));
        }
        else
            return new Risk(getRiskIc(), BigDecimal.ZERO);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
