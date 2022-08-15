CREATE TABLE people
(
    id              INT NOT NULL,
    name            VARCHAR,
    age             INT NOT NULL,
    avto_permission BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE car
(
    id    INT NOT NULL,
    mark  VARCHAR,
    model VARCHAR,
    price REAL,
    PRIMARY KEY (id)
);

CREATE TABLE people_car
(
    id     INT NOT NULL,
    people INT,
    car    INT,
    PRIMARY KEY (id),
    FOREIGN KEY (people) REFERENCES people (id),
    FOREIGN KEY (car) REFERENCES car (id)
);



