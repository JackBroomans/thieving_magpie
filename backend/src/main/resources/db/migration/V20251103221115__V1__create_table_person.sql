
-- Create the Person table
CREATE TABLE IF NOT EXISTS person (
                                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                      family_name VARCHAR(127) NOT NULL,
                                      prefixes_family_name VARCHAR(63) NOT NULL,
                                      givenName VARCHAR(127) NOT NULL,
                                      nickname VARCHAR(31),
                                      initials VARCHAR(31) NOT NULL,
                                      prefixes_family_name VARCHAR(63) NOT NULL,

                                      gender_code CHAR(3) NOT NULL,
                                      use_of_name CHAR(3) NOT NULL,
                                      date_of_birth DATE,
                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for common lookup fields
CREATE INDEX IF NOT EXISTS idx_person_last_name ON person(family_name);

COMMENT ON COLUMN person.id IS 'Unique identifier for the person (UUID), automatic generated.';
COMMENT ON COLUMN person.family_name IS 'The family name of the person. The might be the name of the partner.';
COMMENT ON COLUMN person.prefixes_family_name IS 'Any common prefixes belonging to the family name (e.g., "van", "de")';
COMMENT ON COLUMN person.givenName IS 'The given name (maiden name) at birth of the person';
COMMENT ON COLUMN person.initials iS '';
COMMENT ON COLUMN person.gender_code IS '';
