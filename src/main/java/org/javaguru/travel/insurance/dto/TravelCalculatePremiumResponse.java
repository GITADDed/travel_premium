package org.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;

    private String personLastName;

    private Date agreementDateFrom;

    private Date agreementDateTo;

    @JsonProperty("agreementPremium")
    private BigDecimal agreementPrice;

    private List<Risk> risks;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }
}

