package validations;

import org.javaguru.travel.insurance.core.validations.RequestValidator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.util.Optional;

public class NegativeTestValidation implements RequestValidator {

    private String error = "error";
    private String msg = "message";

    public NegativeTestValidation() {
    }

    public NegativeTestValidation(String error) {
        this.error = error;
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return Optional.of(new ValidationError(error, msg));
    }
}
