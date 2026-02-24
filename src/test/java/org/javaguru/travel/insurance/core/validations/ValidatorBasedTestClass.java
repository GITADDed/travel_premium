package org.javaguru.travel.insurance.core.validations;

import testdto.TravelCalculatePremiumRequestTestDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

abstract class ValidatorBasedTestClass {
    Date from;
    Date to;
    String firstName;
    String lastName;
    String country;
    LocalDate dateOfBirth;
    String medicalRiskLimitLevel;
    TravelCalculatePremiumRequestTestDTO request;

    void setUp() {
        firstName = "Ivan";
        lastName = "Ivanov";
        from = new Date(1900000000000L);
        to = new Date(1900003600000L);
        country = "JAPAN";
        dateOfBirth = LocalDate.now().minusYears(15);
        medicalRiskLimitLevel = "LEVEL_10000";
        request = new TravelCalculatePremiumRequestTestDTO(firstName, lastName, from, to, country, Set.of("TRAVEL_MEDICAL"), dateOfBirth, medicalRiskLimitLevel);
    }

}
