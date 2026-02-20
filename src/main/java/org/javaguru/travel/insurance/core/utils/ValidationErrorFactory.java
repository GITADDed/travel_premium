package org.javaguru.travel.insurance.core.utils;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ValidationErrorFactory {
    private final ErrorCodeUtil errorCodeUtil;

    public ValidationError buildError(String errorCode) {
        return new ValidationError(errorCode, errorCodeUtil.getMessage(errorCode));
    }

}
