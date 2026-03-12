package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.javaguru.travel.insurance.core.repository.AgeCoefficientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgeCoefficientUtilTest {

    @Mock
    AgeCoefficientRepository ageCoefficientRepository;

    @InjectMocks
    AgeCoefficientUtil ageCoefficientUtil;

    @Test
    void shouldReturnAgeCoefficient() {
        when(ageCoefficientRepository.findByAge(Mockito.anyInt())).thenReturn(Optional.of(new AgeCoefficient(new BigDecimal("1.00"))));

        BigDecimal coefficientByBirthDate = ageCoefficientUtil.getCoefficientByBirthDate(LocalDate.now().minusYears(15));
        assertEquals(new BigDecimal("1.00"), coefficientByBirthDate);
    }
}