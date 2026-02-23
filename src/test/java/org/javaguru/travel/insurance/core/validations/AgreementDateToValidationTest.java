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
class AgreementDateToValidationTest extends ValidatorBasedTestClass {

    AgreementDateToValidation validator;

    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidationErrorFactory validationErrorFactory;

    private final String code = "ERROR_CODE_4";
    private final String description = "Field agreementDateTo is empty!";

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new AgreementDateToValidation(validationErrorFactory);
    }

    @Test
    @DisplayName("should return error with null agreementDateTo")
    public void testValidateWithNullAgreementDateTo() {
        Mockito.when(errorCodeUtil.getMessage(Mockito.anyString())).thenReturn(description);

        request.setAgreementDateTo(null);

        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals(code, error.get().errorCode());
        assertEquals(description, error.get().description());
    }

    @Test
    @DisplayName("should return optional empty (it's ok)")
    public void testValidateWithCorrectAgreementDateTo() {
        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isEmpty());
    }

}