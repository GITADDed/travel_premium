package org.javaguru.travel.insurance.core.validations;

import lombok.Getter;
import org.javaguru.travel.insurance.core.utils.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SelectedRisksNullOrEmptyValidation extends AbstractRequestValidation {
    private static final Logger log = LoggerFactory.getLogger(SelectedRisksNullOrEmptyValidation.class);

    @Getter
    private final String code = "ERROR_CODE_5";

    public SelectedRisksNullOrEmptyValidation(ValidationErrorFactory validationErrorFactory) {
        super(validationErrorFactory);
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {

        boolean invalid = request.selectedRisks() == null || request.selectedRisks().isEmpty();

        return super.getErrorOrEmpty(invalid, code, log, request.selectedRisks());
    }
}
