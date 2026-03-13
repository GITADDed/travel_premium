package org.javaguru.travel.insurance.core.repository;

import org.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class ClassifierValueRepositoryTest {

    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    private final String clRiskTitle = "RISK_TYPE";
    private final String prefixIc = "TRAVEL_";

    @Test
    void injectRepositoryAreNotNull() {
        assertNotNull(classifierValueRepository);
    }

    @ParameterizedTest
    @ValueSource(strings = { "TRAVEL_MEDICAL", "TRAVEL_CANCELLATION", "TRAVEL_LOSS_BAGGAGE",
            "TRAVEL_THIRD_PARTY_LIABILITY", "TRAVEL_EVACUATION", "TRAVEL_SPORT_ACTIVITIES" })
    void shouldFindRiskAndCheckIt(String clvIc) {
        searchAndAssertValue(clRiskTitle, clvIc);
    }

    @Test
    void shouldNotFindRiskTypeFake() {
        String clvIc = prefixIc + "FAKE";

        Optional<ClassifierValue> valueOpt = classifierValueRepository.findByClassifierTitleAndIc(clRiskTitle, clvIc);

        assertTrue(valueOpt.isEmpty());
    }

    private void searchAndAssertValue(String clTitle, String clvIc) {
        Optional<ClassifierValue> value = classifierValueRepository.findByClassifierTitleAndIc(clTitle, clvIc);

        assertTrue(value.isPresent());
        assertEquals(clTitle, value.get().getClassifier().getTitle());
        assertEquals(clvIc, value.get().getIc());
    }
}