package org.javaguru.travel.insurance.dto;

import java.math.BigDecimal;

public record Risk(String riskIc, BigDecimal premium) {
}
