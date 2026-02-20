package org.javaguru.travel.insurance.core.validations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.ErrorCodeService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class SelectedRisksValidation implements RequestValidation {
    private static final Logger log = LoggerFactory.getLogger(SelectedRisksValidation.class);
    private final ErrorCodeService errorCodeService;

    @Getter
    private final String code = "ERROR_CODE_5";

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {

        boolean invalid = request.selectedRisks() == null || request.selectedRisks().isEmpty()
                || isElementsBlank(request.selectedRisks());

        if (invalid) {
            log.debug("Validation failed: errorCode={}, reason={}", code, errorCodeService.getMessage(code));
            return Optional.of(new ValidationError(code, errorCodeService.getMessage(code)));
        }

        return Optional.empty();
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
