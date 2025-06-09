USE schuldhulp;

CREATE TABLE clientnummers (
    datum_code VARCHAR(6) PRIMARY KEY COMMENT 'De datum in het formaat jjmmdd waarop het clientnummer is uitgegeven.',
    laatste_volgdeel INT NOT NULL COMMENT 'Het laatst gebruikte volgnummer van het clientnummer op de betrefende datum.'
);
