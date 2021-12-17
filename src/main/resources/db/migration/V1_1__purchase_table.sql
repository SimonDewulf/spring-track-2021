CREATE TABLE Statistic
(
    property VARCHAR NOT NULL PRIMARY KEY,
    value    NUMBER  NOT NULL
);

INSERT INTO Statistic(property, value)
values ('GAMES_WON', 0);
INSERT INTO Statistic(property, value)
values ('GAMES_PLAYED', 0);
INSERT INTO Statistic(property, value)
values ('NUMBER_OF_PLAYERS', 0);
INSERT INTO Statistic(property, value)
values ('NUMBER_OF_MOVES', 0);


CREATE TABLE Game
(
    id     VARCHAR NOT NULL PRIMARY KEY,
    state  VARCHAR NOT NULL,
    moves  NUMBER  NOT NULL
);

