package org.javaguru.travel.insurance.core.validations;

import lombok.Getter;
import org.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.javaguru.travel.insurance.core.repository.ClassifierValueRepository;
import org.javaguru.travel.insurance.core.utils.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SelectedRisksIncorrectRiskTypeValidation extends AbstractRequestValidation {
    private final Logger log = LoggerFactory.getLogger(SelectedRisksIncorrectRiskTypeValidation.class);

    @Getter
    private final String code = "ERROR_CODE_9";

    private final ClassifierValueRepository classifierValueRepository;

    public SelectedRisksIncorrectRiskTypeValidation(ValidationErrorFactory validationErrorFactory,
                                                    ClassifierValueRepository classifierValueRepository) {
        super(validationErrorFactory);
        this.classifierValueRepository = classifierValueRepository;
    }

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        List<ClassifierValue> classifierValues = classifierValueRepository.findAllByClassifierTitle("RISK_TYPE");

        if (request.selectedRisks() == null || request.selectedRisks().isEmpty()) {
            return List.of();
        }

        List<String> classifierValuesIc = classifierValues.stream().map(ClassifierValue::getIc).toList();

        List<ValidationError> errors = new ArrayList<>();

        for (String inputValue : request.selectedRisks())
            if (!classifierValuesIc.contains(inputValue)) {
                errors.add(getErrorWithMessage(code, log, inputValue));
            }
        return errors;
    }
}
