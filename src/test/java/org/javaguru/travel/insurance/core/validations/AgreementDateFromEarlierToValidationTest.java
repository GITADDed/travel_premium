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
class AgreementDateFromEarlierToValidationTest extends ValidatorBasedTestClass {

    AgreementDateFromEarlierToValidation validator;

    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidationErrorFactory errorFactory;

    private final String code = "ERROR_CODE_6";
    private final String description = "Field agreementDateFrom is later than agreementDateTo!";

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new AgreementDateFromEarlierToValidation(errorFactory);
    }

    @Test
    @DisplayName("should return error with dates in incorrect order")
    public void testValidateWithDatesInIncorrectOrder() {
        Mockito.when(errorCodeUtil.getMessage(Mockito.anyString())).thenReturn(description);

        request.setAgreementDateFrom(to);
        request.setAgreementDateTo(from);

        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isPresent());
        assertEquals(code, error.get().getErrorCode());
        assertEquals(description, error.get().getDescription());
    }

    @Test
    @DisplayName("should return optional empty (it's ok)")
    public void testValidateWithDatesIncorrectOrder() {
        Optional<ValidationError> error = validator.validate(request.toDto());

        assertTrue(error.isEmpty());
    }

}