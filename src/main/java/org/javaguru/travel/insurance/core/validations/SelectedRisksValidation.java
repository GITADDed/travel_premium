package org.javaguru.travel.insurance.core.validations;

import lombok.Getter;
import org.javaguru.travel.insurance.core.utils.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class SelectedRisksValidation extends AbstractRequestValidation {
    private static final Logger log = LoggerFactory.getLogger(SelectedRisksValidation.class);

    @Getter
    private final String code = "ERROR_CODE_5";

    public SelectedRisksValidation(ValidationErrorFactory validationErrorFactory) {
        super(validationErrorFactory);
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {

        boolean invalid = request.selectedRisks() == null || request.selectedRisks().isEmpty()
                || isElementsBlank(request.selectedRisks());

        return super.getErrorOrEmpty(invalid, code, log, request.selectedRisks());
    }

    private boolean isElementsBlank(Set<String> selectedRisks) {
        boolean isItBlank = false;
        for (String str : selectedRisks) {
            if (str == null) {
                return true;
            }

            isItBlank = str.isBlank();
            if (isItBlank) {
                return isItBlank;
            }
        }
        return isItBlank;
    }
}
