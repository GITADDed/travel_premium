package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.ErrorCodeService;
import org.javaguru.travel.insurance.core.ValidationErrorFactory;
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
class PersonFirstNameValidationTest extends ValidatorBasedTestClass {
    private PersonFirstNameValidation validator;

    @Mock
    private ErrorCodeService errorCodeService;

    @InjectMocks
    private ValidationErrorFactory validationErrorFactory;

    private final String code = "ERROR_CODE_1";
    private final String description = "Field personFirstName is empty!";

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new PersonFirstNameValidation(validationErrorFactory);
    }

    @Test
    @DisplayName("should return error with null first name in request")
    public void testValidateWithNullFirstName() {
        Mockito.when(errorCodeService.getMessage(Mockito.anyString())).thenReturn(description);

        request.setPersonFirstName(null);
        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals(code, error.get().getErrorCode());
        assertEquals(description, error.get().getDescription());
    }

    @Test
    @DisplayName("should return error with empty string first name in request")
    public void testValidateWithEmptyFirstName() {
        Mockito.when(errorCodeService.getMessage(Mockito.anyString())).thenReturn(description);

        request.setPersonFirstName("");
        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals(code, error.get().getErrorCode());
        assertEquals(description, error.get().getDescription());
    }

    @Test
    @DisplayName("should return empty optional (its ok)")
    public void testValidateWithCorrectFirstName() {
        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isEmpty());
    }
}