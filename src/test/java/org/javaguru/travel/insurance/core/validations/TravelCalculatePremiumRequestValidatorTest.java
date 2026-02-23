package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatePremiumRequestValidatorTest {

    private TravelCalculatePremiumRequestValidator validator;
    private final TravelCalculatePremiumRequest request =
            new TravelCalculatePremiumRequest(null, null,
                    null, null, null, null);

    @Test
    @DisplayName("should return empty list without errors")
    public void testValidate() {
        validator = new TravelCalculatePremiumRequestValidator(List.of(new PositiveTestValidation(),
                new PositiveTestValidation(), new PositiveTestValidation()));

        List<ValidationError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }


    @Test
    @DisplayName("should return list of errors if input is incorrect all")
    public void testValidateWithAllIncorrectInValidation() {
        validator = new TravelCalculatePremiumRequestValidator(List.of(new NegativeTestValidation("ER1"),
                new NegativeTestValidation("ER2")));

        List<ValidationError> errors = validator.validate(request);

        assertEquals(2, errors.size());
        assertEquals(Set.of("ER1", "ER2"), Set.of(errors.get(0).errorCode(), errors.get(1).errorCode()));
    }

    @Test
    @DisplayName("should return list of errors if there are correct fields and incorrect fields")
    public void testValidateWithCorrectAndIncorrectValidation() {
        validator = new TravelCalculatePremiumRequestValidator(List.of(
                new PositiveTestValidation(),
                new NegativeTestValidation("ER1"),
                new PositiveTestValidation(),
                new NegativeTestValidation("ER2")));

        List<ValidationError> errors = validator.validate(request);

        assertEquals(2, errors.size());
        assertEquals(Set.of("ER1", "ER2"), Set.of(errors.get(0).errorCode(), errors.get(1).errorCode()));
    }

}
