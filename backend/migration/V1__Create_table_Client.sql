CREATE TABLE annoying_raven.client
(
    id                   BIGSERIAL PRIMARY KEY       NOT NULL,
    maiden_name          VARCHAR(127),
    prefixes_maiden_name VARCHAR(63),
    family_name          VARCHAR(127),
    prefixes_family_name VARCHAR(63),
    initials             VARCHAR(31)                 NOT NULL,
    nickname             VARCHAR(31)                 NOT NULL,
    prefix_titles        VARCHAR(63),
    suffix_titles        VARCHAR(63),
    preferred_name_use   SMALLINT                    NOT NULL,
    gender_key_value     SMALLINT                    NOT NULL,
    created_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at           TIMESTAMP WITHOUT TIME ZONE NOT NULL

    CONSTRAINT client_family_or_maiden_name_specified CHECK (
        (family_name IS NOT NULL AND trim(family_name) <> '')
            OR
        (maiden_name IS NOT NULL AND trim(maiden_name) <> '')
    )
);


CREATE INDEX IF NOT EXISTS idx_client_family_name ON annoying_raven.client(family_name);
CREATE INDEX IF NOT EXISTS idx_client_maiden_name ON annoying_raven.client(maiden_name);

COMMENT ON TABLE  annoying_raven.client IS
    'The abstract description of a Person. Each specific type of client entity, inherits this description.';
COMMENT ON COLUMN annoying_raven.client.id IS
    'Unique automatic created and immutable identifier of the client (UUID)';
COMMENT ON COLUMN annoying_raven.client.maiden_name IS
    'The given name (Maiden name)';
COMMENT ON COLUMN annoying_raven.client.prefixes_maiden_name IS
    'Any common prefixes belonging to the maiden name (e.g., "van", "de"))';
COMMENT ON COLUMN annoying_raven.client.family_name IS
    'The family name of the client. This could also be the maiden name.';
COMMENT ON COLUMN annoying_raven.client.prefixes_family_name IS
    'Any common prefixes belonging to the family name (e.g., "van", "de")';
COMMENT ON COLUMN annoying_raven.client.initials IS
    'Initials of the.client. The full forenames are not relevant for the abstract class.';
COMMENT ON COLUMN annoying_raven.client.nickname IS
    'Nickname of the client';
COMMENT ON COLUMN annoying_raven.client.prefix_titles IS
    'Applicable academic or noble titles used as prefix (e.g. "Dr.", "Mr.,Msc, Baron")';
COMMENT ON COLUMN annoying_raven.client.suffix_titles IS
    'Applicable academic or other suffixes (e.g., "Msc", "Bsc", "Jr.")';
COMMENT ON COLUMN annoying_raven.client.preferred_name_use IS
    'The key value of the preferred way the client want to use the name.';
COMMENT ON COLUMN annoying_raven.client.gender_key_value IS
    'The key value of the gender of the client.';
COMMENT ON COLUMN annoying_raven.client.created_at IS
    'Date and time of creation of the record.';
COMMENT ON COLUMN annoying_raven.client.updated_at IS
    'Date and time of the last update of the record.';
