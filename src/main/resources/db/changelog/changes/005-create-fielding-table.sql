--liquibase formatted sql

--changeset owengc:005
CREATE TABLE fielding (
    player_id VARCHAR(10) NOT NULL,
    year_id INTEGER NOT NULL,
    stint INTEGER NOT NULL,
    team_id VARCHAR(3) NOT NULL,
    league_id VARCHAR(5),
    position VARCHAR(2) NOT NULL,
    g INTEGER,
    gs INTEGER,
    inn_outs INTEGER,
    po INTEGER,
    a INTEGER,
    e INTEGER,
    dp INTEGER,
    pb INTEGER,
    wp INTEGER,
    sb INTEGER,
    cs INTEGER,
    zr INTEGER,
    PRIMARY KEY (player_id, year_id, stint, team_id, position)
);