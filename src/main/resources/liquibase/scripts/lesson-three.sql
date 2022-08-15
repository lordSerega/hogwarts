-- liquibase formatted sql

--changeset skaplin:1
CREATE INDEX name_index  ON student  (name);
CREATE INDEX name_and_color_faculty_index  ON faculty  (name,color);