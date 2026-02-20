package org.javaguru.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;

import java.util.Optional;

@RequiredArgsConstructor
abstract class AbstractRequestValidation implements RequestValidation {
    private final ValidationErrorFactory validationErrorFactory;

    @Override
    public abstract Optional<ValidationError> validate(TravelCalculatePremiumRequest request);

    protected final Optional<ValidationError> getErrorOrEmpty(boolean invalid, String code, Logger log, Object fieldValue) {
        if (invalid) {
            ValidationError validationError = validationErrorFactory.buildError(code);

            log.debug("Validation failed: code={}, reason={}, field_value={}", code, validationError.getDescription(), safe(fieldValue));
            return Optional.of(validationError);
        }

        return Optional.empty();
    }

    private Object safe(Object v) {
        if (v == null)
            return "null";

        if (v instanceof String s) {
            return "String(len=" + s.length() + ", blank=" + s.isBlank() + ")";
        }

        return v;
    }
}
