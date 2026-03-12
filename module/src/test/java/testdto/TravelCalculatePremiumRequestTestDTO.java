package testdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelCalculatePremiumRequestTestDTO {
    private String personFirstName;
    private String personLastName;
    private Date agreementDateFrom;
    private Date agreementDateTo;
    private String country;

    @JsonProperty("selected_risks")
    private Set<String> selectedRisks;

    private LocalDate personBirthDate;
    private String medicalRiskLimitLevel;

    public TravelCalculatePremiumRequest toDto() {
        return new TravelCalculatePremiumRequest(personFirstName, personLastName,
                agreementDateFrom, agreementDateTo, selectedRisks, country, personBirthDate, medicalRiskLimitLevel);
    }
}
