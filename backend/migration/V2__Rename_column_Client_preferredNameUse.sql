ALTER TABLE client
    ADD preferred_name_use_key_value SMALLINT;

ALTER TABLE client
    ALTER COLUMN preferred_name_use_key_value SET NOT NULL;

ALTER TABLE client
    DROP COLUMN IF EXISTS preferred_name_use;
