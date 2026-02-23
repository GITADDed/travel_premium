package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.domain.Classifier;
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
class CountryExistRequestValidationTest extends ValidatorBasedTestClass {
    @Mock
    ValidationErrorFactory validationErrorFactory;
    @Mock
    ClassifierValueRepository classifierValueRepository;

    @InjectMocks
    CountryExistRequestValidation countryExistRequestValidation;

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void shouldReturnErrorWhenCountryNotExist() {
        when(classifierValueRepository.findByClassifierTitleAndIc(Mockito.anyString(),
                Mockito.anyString())).thenReturn(Optional.empty());

        when(validationErrorFactory.buildErrorWithMessage(Mockito.any(), Mockito.any()))
                .thenReturn(new ValidationError("ERROR_CODE_11", "COUNTRY = UPITER, not exist!"));
        request.setCountry("UPITER");

        Optional<ValidationError> error = countryExistRequestValidation.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_11", error.get().errorCode());
        assertEquals("COUNTRY = UPITER, not exist!", error.get().description());
    }

    @Test
    void shouldReturnEmptyWhenCountryExist() {
        when(classifierValueRepository.findByClassifierTitleAndIc(Mockito.anyString(),
                Mockito.anyString())).thenReturn(Optional.of(
                        new ClassifierValue(1L,
                                new Classifier(1L, "COUNTRY", "", null)
                                , "JAPAN", "", null)));

        Optional<ValidationError> error = countryExistRequestValidation.validate(request.toDto());

        assertTrue(error.isEmpty());
    }
}