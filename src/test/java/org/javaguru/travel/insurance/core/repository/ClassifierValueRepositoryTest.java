package org.javaguru.travel.insurance.core.repository;

import org.javaguru.travel.insurance.core.domain.ClassifierValue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
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

    @Test
    void shouldFindRiskTypeTravelMedical() {
        String clvIc = prefixIc + "MEDICAL";

        searchAndAssertValue(clRiskTitle, clvIc);
    }

    @Test
    void shouldFindRiskTypeTravelCancellation() {
        String clvIc = prefixIc + "CANCELLATION";

        searchAndAssertValue(clRiskTitle, clvIc);
    }

    @Test
    void shouldFindRiskTypeTravelLossBaggage() {
        String clvIc = prefixIc + "LOSS_BAGGAGE";

        searchAndAssertValue(clRiskTitle, clvIc);
    }

    @Test
    void shouldFindRiskTypeTravelThirdPartyLiability() {
        String clvIc = prefixIc + "THIRD_PARTY_LIABILITY";

        searchAndAssertValue(clRiskTitle, clvIc);
    }

    @Test
    void shouldFindRiskTypeTravelTravelEvacuation() {
        String clvIc = prefixIc + "EVACUATION";

        searchAndAssertValue(clRiskTitle, clvIc);
    }

    @Test
    void shouldFindRiskTypeTravelSportActivities() {
        String clvIc = prefixIc + "SPORT_ACTIVITIES";

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