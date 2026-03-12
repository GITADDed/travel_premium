package org.javaguru.travel.insurance.core.repository;

import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryDefaultDayRateRepository extends JpaRepository<CountryDefaultDayRate, Long> {

    @Query("""
                SELECT cddrr FROM CountryDefaultDayRate cddrr
                INNER JOIN cddrr.classifierValue cv
                WHERE cv.ic = :country
            """)
    Optional<CountryDefaultDayRate> findByCountry(@Param("country") String country);
}
