package org.javaguru.travel.insurance.core.repository;

import org.javaguru.travel.insurance.core.domain.AgeCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgeCoefficientRepository extends JpaRepository<AgeCoefficient,Integer> {

    @Query("""
    SELECT a
    FROM AgeCoefficient a
    WHERE :age BETWEEN a.ageFrom AND a.ageTo
""")
    Optional<AgeCoefficient> findByAge(@Param("age") Integer age);
}
