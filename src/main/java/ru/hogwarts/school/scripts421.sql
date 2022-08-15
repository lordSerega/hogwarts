ALTER TABLE student
    ALTER COLUMN "age" SET DEFAULT 20,
    ADD CHECK (age>16),
    ADD UNIQUE (name);
ALTER TABLE faculty
    ADD unique (color,name);
