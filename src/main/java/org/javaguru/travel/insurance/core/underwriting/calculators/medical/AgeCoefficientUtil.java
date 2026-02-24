package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.repository.AgeCoefficientRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
class AgeCoefficientUtil {
    private final AgeCoefficientRepository ageCoefficientRepository;

    BigDecimal getCoefficientByBirthDate(LocalDate birthDate) {
        return ageCoefficientRepository.findByAge(getAge(birthDate)).orElseThrow().getCoefficient();
    }

    private Integer getAge(LocalDate birthDate) {
        return LocalDate.now().getYear()  - birthDate.getYear();
    }
}
