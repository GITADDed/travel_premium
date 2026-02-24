package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.javaguru.travel.insurance.dto.Risk;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    private final AgeCoefficientUtil ageCoefficientUtil;
    private final CountryDefaultDayRateUtil countryDefaultDayRateUtil;
    private final DayCount dayCount;

    @Override
    public Risk calculatePremium(TravelCalculatePremiumRequest request) {
        if (request.selectedRisks().contains(getRiskIc())) {
            BigDecimal premium = dayCount
                    .calculateDaysBetween(request.agreementDateFrom(), request.agreementDateTo())
                    .multiply(countryDefaultDayRateUtil.getDayRateByCountry(request.country()))
                    .multiply(ageCoefficientUtil.getCoefficientByBirthDate(request.personBirthDate()));
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
