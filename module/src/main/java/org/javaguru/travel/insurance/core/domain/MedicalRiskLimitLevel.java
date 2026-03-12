package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "medical_risk_limit_level")
public class MedicalRiskLimitLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "medical_risk_limit_level_ic")
    private String riskLimitIc;

    @Column(name = "coefficient")
    private BigDecimal coefficient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "classifier_value_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_medical_risk_limit_level_classifier"))
    private ClassifierValue classifierValue;

    public MedicalRiskLimitLevel(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
}
