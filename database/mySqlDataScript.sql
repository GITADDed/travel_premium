USE `java-real-practice-insurance`;
INSERT INTO classifiers
    (
     title,
     description
    )
VALUES (
        'RISK_TYPE',
        'travel policy risk type classifier'
       );

INSERT INTO classifier_values (classifier_id, ic, description)
SELECT
    cl.id,
    v.ic,
    v.description
FROM classifiers AS cl
JOIN (
    SELECT 'TRAVEL_MEDICAL' AS ic , 'travel policy medical risk' AS description
    UNION ALL SELECT 'TRAVEL_CANCELLATION', 'travel policy trip cancellation risk'
    UNION ALL SELECT 'TRAVEL_LOSS_BAGGAGE', 'travel policy baggage lose risk'
    UNION ALL SELECT 'TRAVEL_THIRD_PARTY_LIABILITY', 'travel policy third party liability risk'
    UNION ALL SELECT 'TRAVEL_EVACUATION', 'travel policy baggage lose risk'
    UNION ALL SELECT 'TRAVEL_SPORT_ACTIVITIES', 'travel policy sport activities risk'
) v
WHERE title = 'RISK_TYPE';
