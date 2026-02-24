CREATE TABLE classifiers
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    title       VARCHAR(200) NOT NULL,
    description VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_classifiers_title ON classifiers (title);

CREATE TABLE classifiers_value
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    classifier_id BIGINT       NOT NULL,
    ic            VARCHAR(200) NOT NULL,
    description   VARCHAR(500) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE classifiers_value
    ADD FOREIGN KEY (classifier_id) REFERENCES classifiers (id);

CREATE UNIQUE INDEX ix_classifier_values_ic ON classifiers_value (ic);

CREATE TABLE country_default_day_rate
(
    id                  BIGINT        NOT NULL AUTO_INCREMENT,
    day_rate            DECIMAL(5, 2) NOT NULL,
    classifier_value_id BIGINT        NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE country_default_day_rate
    ADD FOREIGN KEY (classifier_value_id) REFERENCES classifiers_value (id);

CREATE TABLE age_coefficient
(
    id          BIGINT        NOT NULL AUTO_INCREMENT,
    age_from    SMALLINT      NOT NULL,
    age_to      SMALLINT      NOT NULL,
    coefficient DECIMAL(5, 2) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT chk_age_range CHECK (age_from <= age_to)
);

CREATE UNIQUE INDEX ix_age_coefficient_age_from ON age_coefficient (age_from);

CREATE TABLE medical_risk_limit_level
(
    id                          BIGINT       NOT NULL AUTO_INCREMENT,
    medical_risk_limit_level_ic VARCHAR(200) NOT NULL,
    coefficient                 DECIMAL(5, 2),
    classifier_value_id         BIGINT       NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE medical_risk_limit_level
    ADD FOREIGN KEY (classifier_value_id) REFERENCES classifiers_value (id);

CREATE UNIQUE INDEX ix_medical_risk_limit_level_classifier_value_id ON medical_risk_limit_level (classifier_value_id);
