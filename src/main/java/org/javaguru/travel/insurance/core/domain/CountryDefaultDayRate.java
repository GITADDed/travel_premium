package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class CountryDefaultDayRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_rate", nullable = false, scale = 2, precision = 5)
    private BigDecimal dayRate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "classifier_value_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_country_default_day_rate_classifiers_value")
    )
    private ClassifierValue classifierValue;

    public CountryDefaultDayRate(BigDecimal dayRate) {
        this.dayRate = dayRate;
    }
}
