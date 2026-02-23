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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryNotNullRequestValidationTest extends ValidatorBasedTestClass {
    @Mock
    ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    CountryNotNullRequestValidation countryNotNullRequestValidation;

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void shouldReturnErrorWhenTravelMedicalRiskAndCountryNull() {

        when(validationErrorFactory.buildError(Mockito.any()))
                .thenReturn(new ValidationError("ERROR_CODE_10", "Field country is empty, when chose TRAVEL_MEDICAL risk!"));

        request.setCountry(null);

        Optional<ValidationError> error = countryNotNullRequestValidation.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_10", error.get().errorCode());
        assertEquals("Field country is empty, when chose TRAVEL_MEDICAL risk!", error.get().description());
    }

    @Test
    void shouldReturnEmptyWhenTravelMedicalRiskAndCountryExist() {
        Optional<ValidationError> error = countryNotNullRequestValidation.validate(request.toDto());
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnEmptyWhenRiskNotTravelMedicalAndCountryExist() {
        request.setSelectedRisks(Set.of("NO_TRAVEL_MEDICAL"));
        Optional<ValidationError> error = countryNotNullRequestValidation.validate(request.toDto());
        assertTrue(error.isEmpty());
    }

}