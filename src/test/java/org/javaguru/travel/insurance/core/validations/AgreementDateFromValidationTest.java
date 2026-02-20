package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.utils.ErrorCodeService;
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
class AgreementDateFromValidationTest extends ValidatorBasedTestClass {
    AgreementDateFromValidation validator;

    @Mock
    private ErrorCodeService errorCodeService;

    @InjectMocks
    private ValidationErrorFactory validationErrorFactory;

    private final String code = "ERROR_CODE_3";
    private final String description = "Field agreementDateFrom is empty!";

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new AgreementDateFromValidation(validationErrorFactory);
    }

    @Test
    @DisplayName("should return error with null agreementDateFrom")
    public void testValidateWithFirstAndLastNameAndNullAgreementDateFrom() {
        Mockito.when(errorCodeService.getMessage(Mockito.anyString())).thenReturn(description);

        request.setAgreementDateFrom(null);

        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals(code, error.get().getErrorCode());
        assertEquals(description, error.get().getDescription());
    }

    @Test
    @DisplayName("should return optional empty (it's ok)")
    public void testValidateWithCorrectAgreementDateFrom() {
        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isEmpty());
    }

}