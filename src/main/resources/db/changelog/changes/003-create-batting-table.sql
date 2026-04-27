--liquibase formatted sql

--changeset owengc:003
CREATE TABLE batting (
    player_id VARCHAR(10) NOT NULL,
    year_id INTEGER NOT NULL,
    stint INTEGER NOT NULL,
    team_id VARCHAR(3) NOT NULL,
    league_id VARCHAR(5),
    g INTEGER,
    ab INTEGER,
    r INTEGER,
    h INTEGER,
    doubles INTEGER,
    triples INTEGER,
    hr INTEGER,
    rbi INTEGER,
    sb INTEGER,
    cs INTEGER,
    bb INTEGER,
    so INTEGER,
    ibb INTEGER,
    hbp INTEGER,
    sh INTEGER,
    sf INTEGER,
    gidp INTEGER,
    PRIMARY KEY (player_id, year_id, stint, team_id)
);