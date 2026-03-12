package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.javaguru.travel.insurance.core.repository.ClassifierValueRepository;
import org.javaguru.travel.insurance.core.utils.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MedicalLimitIsExistRequestValidation extends AbstractRequestValidation {
    private final Logger log = LoggerFactory.getLogger(MedicalLimitIsExistRequestValidation.class);
    private final String code = "ERROR_CODE_15";
    private final ClassifierValueRepository classifierValueRepository;

    public MedicalLimitIsExistRequestValidation(ValidationErrorFactory validationErrorFactory, ClassifierValueRepository classifierValueRepository) {
        super(validationErrorFactory);
        this.classifierValueRepository = classifierValueRepository;
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (request.medicalRiskLimitLevel() == null)
            return Optional.empty();
        Optional<ClassifierValue> classifierValue = classifierValueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", request.medicalRiskLimitLevel());
        boolean invalid = classifierValue.isEmpty();
        return super.getErrorOrEmptyWithUpdatedMessage(invalid, code, log, request.medicalRiskLimitLevel(), request.medicalRiskLimitLevel());
    }
}
