package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.javaguru.travel.insurance.core.repository.ClassifierValueRepository;
import org.javaguru.travel.insurance.core.utils.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicalLimitIsExistRequestValidationTest extends ValidatorBasedTestClass {
    @Mock
    ValidationErrorFactory validationErrorFactory;

    @Mock
    ClassifierValueRepository classifierValueRepository;

    @InjectMocks
    MedicalLimitIsExistRequestValidation validator;

    @BeforeEach
    void setup() {
        super.setUp();
    }

    @Test
    void shouldReturnEmptyWhenWhenMedicalLimitExist() {
        when(classifierValueRepository.findByClassifierTitleAndIc(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(new ClassifierValue("LEVEL_10000")));

        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnErrorWhenMedicalLimitIsNotExist() {
        request.setMedicalRiskLimitLevel("LUVEL_10000");
        when(classifierValueRepository.findByClassifierTitleAndIc(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.empty());
        when(validationErrorFactory.buildErrorWithMessage(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new ValidationError("ERROR_CODE_15",
                        "medicalRiskLimitLevel = {LUVEL_10000}, not exist!"));

        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_15", error.get().errorCode());
        assertEquals("medicalRiskLimitLevel = {LUVEL_10000}, not exist!", error.get().description());
    }
}