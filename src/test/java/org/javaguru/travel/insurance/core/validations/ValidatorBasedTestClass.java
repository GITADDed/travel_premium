package org.javaguru.travel.insurance.core.validations;

import testdto.TravelCalculatePremiumRequestTestDTO;

import java.util.Date;
import java.util.Set;

abstract class ValidatorBasedTestClass {
    Date from;
    Date to;
    String firstName;
    String lastName;
    String country;
    TravelCalculatePremiumRequestTestDTO request;

    void setUp() {
        firstName = "Ivan";
        lastName = "Ivanov";
        from = new Date(1900000000000L);
        to = new Date(1900003600000L);
        country = "JAPAN";
        request = new TravelCalculatePremiumRequestTestDTO(firstName, lastName, from, to, country, Set.of("TRAVEL_MEDICAL"));
    }

}
