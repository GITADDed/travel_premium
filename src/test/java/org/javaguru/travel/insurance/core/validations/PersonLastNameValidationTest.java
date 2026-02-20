package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.utils.ErrorCodeUtil;
import org.javaguru.travel.insurance.core.utils.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonLastNameValidationTest extends ValidatorBasedTestClass {
    private PersonLastNameValidation validator;

    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidationErrorFactory validationErrorFactory;

    private final String code = "ERROR_CODE_2";
    private final String description = "Field personLastName is empty!";

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new PersonLastNameValidation(validationErrorFactory);
    }

    @Test
    @DisplayName("should return error with empty last name in request")
    public void testValidateWithEmptyLastName() {
        Mockito.when(errorCodeUtil.getMessage(Mockito.anyString())).thenReturn(description);

        request.setPersonLastName("");

        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals(code, error.get().getErrorCode());
        assertEquals(description, error.get().getDescription());
    }

    @Test
    @DisplayName("should return error with null last name in request")
    public void testValidateWithNullLastName() {
        Mockito.when(errorCodeUtil.getMessage(Mockito.anyString())).thenReturn(description);

        request.setPersonLastName(null);

        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals(code, error.get().getErrorCode());
        assertEquals(description, error.get().getDescription());
    }

    @Test
    @DisplayName("should return empty optional (its ok)")
    public void testValidateWithCorrectLastName() {
        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isEmpty());
    }

}