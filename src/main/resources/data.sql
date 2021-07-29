DROP TABLE IF EXISTS processing;

CREATE TABLE processing
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    date_begin     DATE      NOT NULL,
    count_work_day INT       NOT NULL,
    date_result    DATE,
    created        TIMESTAMP NOT NULL,
    updated        TIMESTAMP,
    status         INT -- 200 (OK), 500 (ERROR), 102 (PROCESSING)
);

DROP SEQUENCE IF EXISTS processing_seq;

