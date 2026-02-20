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
class AgreementDateFromLaterNowValidationTest extends ValidatorBasedTestClass {

    AgreementDateFromLaterNowValidation validator;

    @Mock
    private ErrorCodeService errorCodeService;

    @InjectMocks
    private ValidationErrorFactory validationErrorFactory;

    private final String code = "ERROR_CODE_7";
    private final String description = "Field agreementDateFrom is earlier than now!";

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new AgreementDateFromLaterNowValidation(validationErrorFactory);
    }

    @Test
    @DisplayName("should return list of error with agreement date from in past")
    public void testValidateWithAgreementDateFromInPast() {
        Mockito.when(errorCodeService.getMessage(Mockito.anyString())).thenReturn(description);

        Date fromInPast = new Date(0);

        request.setAgreementDateFrom(fromInPast);

        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals(code, error.get().getErrorCode());
        assertEquals(description, error.get().getDescription());
    }

    @Test
    @DisplayName("should return list of error with agreement date from in past")
    public void testValidateWithCorrectAgreementDateFrom() {
        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isEmpty());
    }

}