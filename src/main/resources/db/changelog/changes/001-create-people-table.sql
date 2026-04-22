--liquibase formatted sql

--changeset owengc:001
CREATE TABLE people (
    player_id VARCHAR(10) PRIMARY KEY,
    birth_year INTEGER,
    birth_month INTEGER,
    birth_day INTEGER,
    birth_city VARCHAR(100),
    birth_country VARCHAR(50),
    birth_state VARCHAR(50),
    death_year INTEGER,
    death_month INTEGER,
    death_day INTEGER,
    death_country VARCHAR(50),
    death_state VARCHAR(50),
    death_city VARCHAR(100),
    name_first VARCHAR(50),
    name_last VARCHAR(50),
    name_given VARCHAR(100),
    weight INTEGER,
    height INTEGER,
    bats CHAR(1),
    throws CHAR(1),
    debut DATE,
    final_game DATE,
    bbref_id VARCHAR(20),
    retro_id VARCHAR(20)
);