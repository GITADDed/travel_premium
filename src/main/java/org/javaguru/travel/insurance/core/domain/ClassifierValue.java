package org.javaguru.travel.insurance.core.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
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
}
