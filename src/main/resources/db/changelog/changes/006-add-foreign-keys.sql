--liquibase formatted sql

--changeset owengc:006

-- Batting → People
ALTER TABLE batting
    ADD CONSTRAINT fk_batting_player
        FOREIGN KEY (player_id)
            REFERENCES people (player_id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;

-- Batting → Teams (composite)
ALTER TABLE batting
    ADD CONSTRAINT fk_batting_team
        FOREIGN KEY (year_id, team_id)
            REFERENCES teams (year_id, team_id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;

-- Pitching → People
ALTER TABLE pitching
    ADD CONSTRAINT fk_pitching_player
        FOREIGN KEY (player_id)
            REFERENCES people (player_id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;

-- Pitching → Teams (composite)
ALTER TABLE pitching
    ADD CONSTRAINT fk_pitching_team
        FOREIGN KEY (year_id, team_id)
            REFERENCES teams (year_id, team_id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;

-- Fielding → People
ALTER TABLE fielding
    ADD CONSTRAINT fk_fielding_player
        FOREIGN KEY (player_id)
            REFERENCES people (player_id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;

-- Fielding → Teams (composite)
ALTER TABLE fielding
    ADD CONSTRAINT fk_fielding_team
        FOREIGN KEY (year_id, team_id)
            REFERENCES teams (year_id, team_id)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT;

