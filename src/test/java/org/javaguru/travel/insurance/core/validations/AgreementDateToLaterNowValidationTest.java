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

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgreementDateToLaterNowValidationTest extends ValidatorBasedTestClass {

    AgreementDateToLaterNowValidation validator;

    @Mock
    private ErrorCodeService errorCodeService;

    @InjectMocks
    private ValidationErrorFactory validationErrorFactory;

    private final String code = "ERROR_CODE_8";
    private final String description = "Field agreementDateTo is earlier than now!";

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new AgreementDateToLaterNowValidation(validationErrorFactory);
    }

    @Test
    @DisplayName("should return error with agreement date to in past")
    public void testValidateWithAgreementDateToInPast() {
        Mockito.when(errorCodeService.getMessage(Mockito.anyString())).thenReturn(description);

        Date toInPast = new Date(0);

        request.setAgreementDateTo(toInPast);

        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals(code, error.get().getErrorCode());
        assertEquals(description, error.get().getDescription());

    }

    @Test
    @DisplayName("should return error with agreement date to in past")
    public void testValidateWithCorrectAgreementDateTo() {
        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isEmpty());
    }
}
