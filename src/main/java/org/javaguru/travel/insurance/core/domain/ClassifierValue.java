package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classifiers_value")
public class ClassifierValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "classifier_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_classifier_values_classifier")
    )
    private Classifier classifier;

    @Column(name = "ic", nullable = false, length = 200)
    private String ic;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @OneToMany(mappedBy = "classifierValue")
    private List<CountryDefaultDayRate> countryDefaultDayRates = new ArrayList<>();

    public ClassifierValue(String ic) {
        this.ic = ic;
    }
}
