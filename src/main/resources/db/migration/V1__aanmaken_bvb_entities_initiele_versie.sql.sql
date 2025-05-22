# V1
#
#

USE thieving_magpie;

-- Client
CREATE TABLE client (
    id CHAR(36) PRIMARY KEY COMMENT '',
    clientnummer CHAR(14) NOT NULL UNIQUE COMMENT 'Unieke clientnummer zoals dat wordt gebruikt als, min of meer leesbare versie van de primary key.',
    familienaam VARCHAR(127) NOT NULL COMMENT 'Verplichte specificatie van de familienaam, indien van toepassing en gewenst inclusief de familienaam van de partner, volgens het door de cliënt gewenste naamgebruik, maar zonder voorvoegsels van het eerste naamdeel.',
    voorvoegsel VARCHAR(31) COMMENT 'De bij de familienaam (eerst gekozen volgens het naamgebruik) behorende voorvoegsels.',
    voorletters VARCHAR(31) NOT NULL COMMENT 'Verplichte specificatie van de voorletters van de cliënt.',
    aanhef VARCHAR(32) COMMENT 'De aanhef, inclusief de titels indien gewenst, die in de correspondentievorm van de samengestelde naam wordt gebruikt.',

    INDEX idx_client_clientnummer (clientnummer),
    INDEX idx_client_familienaam (familienaam)
);

-- Dossier
CREATE TABLE dossier (
    id CHAR(36) PRIMARY KEY COMMENT 'Unieke identificatie van het dossier, uitgegeven door de applicatie als UUID.',
    client_id CHAR(36) NOT NULL COMMENT 'Referentie naar de client waar het dossier betrekking op heeft.',
    naam VARCHAR(80) NOT NULL COMMENT 'Verplichte (unieke) naam van het dossier, waarin de naam van de client is verwerkt.',

    FOREIGN KEY (client_id) REFERENCES client(id),

    INDEX idx_client_dossier (client_id)
);

-- Schuldeiser
CREATE TABLE schuldeiser (
    id CHAR(36) PRIMARY KEY COMMENT 'Unieke identificatie van de schuldeiser, uitgegeven door de applicatie als UUID.',
    naam VARCHAR(255) NOT NULL COMMENT 'Verplichte (unieke) naam van de schuldeiser, waarin de naam van de client is verwerkt.',
    ibannummer VARCHAR(34) NOT NULL COMMENT 'Verplicht IBAN-bankrekeningnummer van de schuldeiser.'
);

-- Budget
CREATE TABLE budget (
    id CHAR(36) PRIMARY KEY COMMENT 'Unieke identificatie van het budget, uitgegeven door de applicatie als UUID.',
    naam VARCHAR(255) COMMENT '',
    startsaldo DECIMAL(12,2) NOT NULL COMMENT 'Het verplicht gespecificeerde initiële grootte van het budget',
    bijgewerkt_op DATETIME COMMENT '',
    vorig_saldo DECIMAL(12,2) COMMENT 'Het beschikbare saldo van het budget voordat de betaling werd verricht.',
    actuele_saldo DECIMAL(12,2) NOT NULL COMMENT 'Het nog beschikbare saldo van het budget, nadat de betaling is verricht.'
);

-- Betaling
CREATE TABLE betaling (
        id CHAR(36) PRIMARY KEY COMMENT 'Unieke identificatevan de betaling, uitgegeven door de applicatie als UUID.',
        dossier_id CHAR(36) NOT NULL COMMENT 'Verplichte referentie naar id van het dossier.',
        schuldeiser_id CHAR(36) NOT NULL COMMENT 'Verplichte referentie naar id van de schuldeiser.',
        bedrag DECIMAL(12,2) NOT NULL COMMENT 'Het verplicht gespecificeerde bedrag wat is betaald.',
        omschrijving TEXT NOT NULL COMMENT 'De verplichte omschrijving van de reden van de betaling.',
        budget_id CHAR(36) COMMENT 'Een optionele refrentie naar het budget waaruit de betaling is gedaan.',
        betaald_op DATETIME COMMENT 'De verplicht gespecificeerde datum waarop de betaling is verricht.',
        aangemaakt_op DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Het tijdstip waarop de schuldhulpverlener de betaling heeft aangemaakt, waardoor dit Bericht van Betaling automatisch is gegenereerd.',

        FOREIGN KEY (dossier_id) REFERENCES dossier(id),
        FOREIGN KEY (schuldeiser_id) REFERENCES schuldeiser(id),
        FOREIGN KEY (budget_id) REFERENCES budget(id),

        INDEX idx_betaling_dossier (dossier_id),
        INDEX idx_betaling_schuldeiser (schuldeiser_id),
        INDEX idx_betaling_budget (budget_id)
);

-- Bericht van betaling (Simulatie van een materialized view in MySql)
CREATE TABLE bericht_van_betaling AS
SELECT DISTINCT
    b.id AS betaling_id,
    b.bedrag AS betaling_bedrag,
    b.omschrijving AS betaling_omschrijving,
    b.betaald_op AS betaling_betaald_op,
    d.naam AS dossier_naam,
    c.familienaam AS client_familienaam,
    bu.id AS budget_id,
    bu.naam AS budget_naam,
    bu.vorig_saldo AS budget_vorig_saldo,
    bu.actuele_saldo AS budget_actuele_saldo,
    s.id AS schuldeiser_id,
    s.naam AS schuldeiser_naam,
    s.ibannummer AS schuldeiser_ibannummer
FROM betaling b
         JOIN dossier d ON b.dossier_id = d.id
         JOIN client c ON d.client_id = c.id
         JOIN schuldeiser s ON b.schuldeiser_id = s.id
         LEFT JOIN budget bu ON b.budget_id = bu.id;

ALTER TABLE bericht_van_betaling ADD COLUMN
    id VARCHAR(36) UNIQUE COMMENT '';

CREATE TRIGGER betaling_na_insert
    AFTER INSERT ON betaling
    FOR EACH ROW
BEGIN
    INSERT INTO bericht_van_betaling (
        betaling_id,
        betaling_bedrag,
        betaling_omschrijving,
        betaling_betaald_op,
        dossier_naam,
        client_familienaam,
        budget_id,
        budget_naam,
        budget_vorig_saldo,
        budget_actuele_saldo,
        schuldeiser_id,
        schuldeiser_naam,
        schuldeiser_ibannummer
    )
    SELECT
        b.id,
        b.bedrag,
        b.omschrijving,
        b.betaald_op,
        d.naam,
        c.familienaam,
        bu.id,
        bu.naam,
        bu.vorig_saldo,
        bu.actuele_saldo,
        s.id,
        s.naam,
        s.ibannummer
    FROM betaling b
             JOIN dossier d ON b.dossier_id = d.id
             JOIN client c ON d.client_id = c.id
             JOIN schuldeiser s ON b.schuldeiser_id = s.id
             LEFT JOIN budget bu ON b.budget_id = bu.id
    WHERE b.id = NEW.id;
END;



# -- Bericht van betaling
# CREATE TABLE bericht_van_betaling
# (
#         id CHAR(36) PRIMARY KEY COMMENT 'Unieke identificatevan de betaling, uitgegeven door de applicatie als UUID.',
#         betaling_id CHAR(36) NOT NULL COMMENT 'Verplicht te specificeren referentie naar de betaling waarop het bericht betrekking heeft.',
#         dossier_id CHAR(36) NOT NULL COMMENT 'Verplichte referentie naar id van het dossier.',
#         schuldeiser_id CHAR(36) NOT NULL COMMENT 'Verplichte referentie naar id van de schuldeiser.',
#         status VARCHAR(50) NOT NULL COMMENT 'De actuele status van het bericht van betaling afkomstig van de enumeratie.',
#         aangemaakt_op DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Het tijdstip waarop de betaling is aangemaakt. Wordt automatisch gespecificeerd bij het aanmaken van het bericht.',
#         opmerking TEXT COMMENT 'Een eventuele toelichtig of aanvulling van de schuldhulpverlener met betrekkking tot de betaling',
#
#         FOREIGN KEY (dossier_id) REFERENCES dossier (id),
#         FOREIGN KEY (schuldeiser_id) REFERENCES schuldeiser (id),
#         FOREIGN KEY (betaling_id) REFERENCES betaling(id),
#
#         INDEX idx_bericht_van_betaling_dossier (dossier_id),
#         INDEX idx_bericht_van_betaling_betaling (betaling_id),
#         INDEX idx_bericht_van_betaling_schuldeiser (schuldeiser_id)
# );
