package org.javaguru.travel.insurance.dto;

import java.math.BigDecimal;
import java.util.List;

public record SummaryRisks(BigDecimal premium, List<Risk> risks) {
}
