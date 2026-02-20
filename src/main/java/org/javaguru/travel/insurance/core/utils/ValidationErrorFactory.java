package org.javaguru.travel.insurance.core.utils;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ValidationErrorFactory {
    private final ErrorCodeService errorCodeService;

    public ValidationError buildError(String errorCode) {
        return new ValidationError(errorCode, errorCodeService.getMessage(errorCode));
    }

}
