
-- Create the Person table
CREATE TABLE IF NOT EXISTS annoying_raven.person (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    given_name VARCHAR(127) NOT NULL,
    prefixes_given_name VARCHAR(63),
    family_name VARCHAR(127),
    prefixes_family_name VARCHAR(63),
    initials VARCHAR(31) NOT NULL,
    nickname VARCHAR(31),
    prefix_titles VARCHAR(63),
    suffix_titles VARCHAR(63),
    preferred_name_use SMALLINT NOT NULL,
    gender SMALLINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_person_given_name ON annoying_raven.person(family_name);

COMMENT ON TABLE  annoying_raven.person IS 'The abstract description of a Person. Each specific type of person entity, inherits this description.';
COMMENT ON COLUMN annoying_raven.person.id IS 'Unique automatic created and immutable identifier of the person (UUID)';
COMMENT ON COLUMN annoying_raven.person.given_Name IS 'The given name (family name given at birth aka maiden name)';
COMMENT ON COLUMN annoying_raven.person.prefixes_given_Name IS 'Any common prefixes belonging to the given name (e.g., "van", "de"))';
COMMENT ON COLUMN annoying_raven.person.family_name IS 'The family name of the person. This might be the name of the partner.';
COMMENT ON COLUMN annoying_raven.person.prefixes_family_name IS 'Any common prefixes belonging to the family name (e.g., "van", "de")';
COMMENT ON COLUMN annoying_raven.person.initials IS 'Initials of the person. The full forenames are not relevant for the abstract class.';
COMMENT ON COLUMN annoying_raven.person.nickname IS 'Nickname of the person';
COMMENT ON COLUMN annoying_raven.person.prefix_titles IS 'Applicable academic or noble titles used as prefix (e.g. "Dr.", "Mr.,Msc, Baron")';
COMMENT ON COLUMN annoying_raven.person.suffix_titles IS 'Applicable academic or other suffixes (e.g., "Msc", "Bsc", "Jr.")';
COMMENT ON COLUMN annoying_raven.person.preferred_name_use IS 'The preferred way the person want to use the name.';
COMMENT ON COLUMN annoying_raven.person.gender IS 'The gender of the person.';
COMMENT ON COLUMN annoying_raven.person.created_at IS 'Date and time of creation of the record.';
COMMENT ON COLUMN annoying_raven.person.updated_at IS 'Date and time of the last update of the record.';
