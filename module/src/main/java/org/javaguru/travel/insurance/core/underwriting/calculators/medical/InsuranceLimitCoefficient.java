package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.repository.MedicalRiskLimitLevelRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Component
class InsuranceLimitCoefficient {
    private final MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    BigDecimal getInsuranceLimitCoefficient(String limitIc) {
        return medicalRiskLimitLevelRepository.findByRiskLimitIc(limitIc).orElseThrow().getCoefficient();
    }
}
