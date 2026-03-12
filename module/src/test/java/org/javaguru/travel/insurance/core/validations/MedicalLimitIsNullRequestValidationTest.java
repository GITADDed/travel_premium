package org.javaguru.travel.insurance.core.validations;

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
class MedicalLimitIsNullRequestValidationTest extends ValidatorBasedTestClass {

    @Mock
    ValidationErrorFactory errorFactory;

    @InjectMocks
    MedicalLimitIsNullRequestValidation medicalLimitIsNullRequestValidation;

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void shouldReturnEmptyWhenMedicalLimitIsCorrect() {
        Optional<ValidationError> error = medicalLimitIsNullRequestValidation.validate(request.toDto());

        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnErrorWhenMedicalLimitIsNull() {
        request.setMedicalRiskLimitLevel(null);

        when(errorFactory.buildError(Mockito.anyString()))
                .thenReturn(new ValidationError(
                        "ERROR_CODE_14", "Field medicalRiskLimitLevel is empty!"));

        Optional<ValidationError> error = medicalLimitIsNullRequestValidation.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_14", error.get().errorCode());
        assertEquals("Field medicalRiskLimitLevel is empty!", error.get().description());
    }

    @Test
    void shouldReturnErrorWhenMedicalLimitIsEmpty() {
        request.setMedicalRiskLimitLevel("");

        when(errorFactory.buildError(Mockito.anyString()))
                .thenReturn(new ValidationError(
                        "ERROR_CODE_14", "Field medicalRiskLimitLevel is empty!"));

        Optional<ValidationError> error = medicalLimitIsNullRequestValidation.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_14", error.get().errorCode());
        assertEquals("Field medicalRiskLimitLevel is empty!", error.get().description());
    }
}