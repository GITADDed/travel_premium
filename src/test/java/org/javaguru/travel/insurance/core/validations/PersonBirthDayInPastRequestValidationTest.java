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

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonBirthDayInPastRequestValidationTest extends ValidatorBasedTestClass {

    @Mock
    ValidationErrorFactory validationErrorFactory;

    @InjectMocks
    PersonBirthDayInPastRequestValidation personBirthDayInPastRequestValidation;

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void shouldReturnErrorWhenPersonBirthDateInFuture() {
        when(validationErrorFactory.buildError(Mockito.any())).thenReturn(new ValidationError(
                "ERROR_CODE_13", "Field personDateBirth is future date!"));

        request.setPersonBirthDate(LocalDate.now().plusYears(5));

        Optional<ValidationError> error = personBirthDayInPastRequestValidation.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals("ERROR_CODE_13", error.get().errorCode());
        assertEquals("Field personDateBirth is future date!", error.get().description());
    }

    @Test
    void shouldReturnEmptyWhenPersonBirthDateIsCorrect() {
        Optional<ValidationError> error = personBirthDayInPastRequestValidation.validate(request.toDto());

        assertTrue(error.isEmpty());
    }
}