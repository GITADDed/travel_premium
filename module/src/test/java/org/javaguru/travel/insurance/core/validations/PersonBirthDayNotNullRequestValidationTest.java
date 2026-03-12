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
class PersonBirthDayNotNullRequestValidationTest extends ValidatorBasedTestClass {

    @Mock
    ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    PersonBirthDayNotNullRequestValidation personBirthDayNotNullRequestValidation;

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void shouldReturnErrorWhenPersonBirthDateIsNull() {
        when(validationErrorFactory.buildError(Mockito.any())).thenReturn(
                new ValidationError("ERROR_CODE_12", "Field personDateBirth is empty!"));
        request.setPersonBirthDate(null);

        Optional<ValidationError> validation = personBirthDayNotNullRequestValidation.validate(request.toDto());

        assertTrue(validation.isPresent());
        assertEquals("ERROR_CODE_12", validation.get().errorCode());
        assertEquals("Field personDateBirth is empty!", validation.get().description());
    }

    @Test
    void shouldReturnEmptyWhenPersonBirthDateIsCorrect() {
        Optional<ValidationError> validation = personBirthDayNotNullRequestValidation.validate(request.toDto());

        assertTrue(validation.isEmpty());
    }
}