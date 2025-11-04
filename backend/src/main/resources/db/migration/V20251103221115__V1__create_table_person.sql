
-- Create the Person table
CREATE TABLE IF NOT EXISTS person (
                                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                      family_name VARCHAR(100) NOT NULL,
                                      prefix_family_name VARCHAR(100) NOT NULL,
                                      givenName VARCHAR(100) NOT NULL,
                                      surnames VARCHAR(100) NOT NULL,
                                      prefixes_family_name VARCHAR(100) NOT NULL,
                                      gender_code CHAR(3) NOT NULL,
                                      date_of_birth DATE,
                                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for common lookup fields
CREATE INDEX IF NOT EXISTS idx_person_last_name ON person(family_name);

COMMENT ON COLUMN person.id IS 'Unique identifier for the person (UUID), automatic generated.';
COMMENT ON COLUMN person.family_name IS 'The family name of the person. The might be the name of the partner.';
COMMENT ON COLUMN person.prefix_family_name IS '';
COMMENT ON COLUMN person.givenName IS '';
COMMENT ON COLUMN person.surnames iS '';
COMMENT ON COLUMN person.gender_code IS '';
