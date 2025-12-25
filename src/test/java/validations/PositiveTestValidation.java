package validations;

import org.javaguru.travel.insurance.core.validations.RequestValidator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.util.Optional;

public class PositiveTestValidation implements RequestValidator {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return Optional.empty();
    }
}
