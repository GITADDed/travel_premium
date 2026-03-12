package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.javaguru.travel.insurance.core.repository.MedicalRiskLimitLevelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InsuranceLimitCoefficientTest {
    @Mock
    MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    @InjectMocks
    InsuranceLimitCoefficient insuranceLimitCoefficient;

    @Test
    void shouldReturnCoefficient() {
        when(medicalRiskLimitLevelRepository.findByRiskLimitIc(Mockito.anyString()))
                .thenReturn(Optional.of(new MedicalRiskLimitLevel(new BigDecimal("1.00"))));

        BigDecimal result = insuranceLimitCoefficient.getInsuranceLimitCoefficient("LEVEL_10000");

        assertEquals(new BigDecimal("1.00"), result);
        verify(medicalRiskLimitLevelRepository, Mockito.times(1)).findByRiskLimitIc(Mockito.anyString());
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenCoefficientNotFound() {
        when(medicalRiskLimitLevelRepository.findByRiskLimitIc(Mockito.anyString())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> insuranceLimitCoefficient.getInsuranceLimitCoefficient("LEVEL_10000"));
        verify(medicalRiskLimitLevelRepository, Mockito.times(1)).findByRiskLimitIc(Mockito.anyString());
    }
}