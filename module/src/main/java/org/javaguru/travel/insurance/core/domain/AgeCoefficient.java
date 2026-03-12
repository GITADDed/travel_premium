package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "age_coefficient")
public class AgeCoefficient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age_from", nullable = false)
    private Short ageFrom;

    @Column(name = "age_to", nullable = false)
    private Short ageTo;

    @Column(name = "coefficient", nullable = false, scale = 2, precision = 5)
    private BigDecimal coefficient;

    public AgeCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }
}
