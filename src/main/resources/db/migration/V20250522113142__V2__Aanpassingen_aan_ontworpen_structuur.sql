USE thieving_magpie;

-- Koppeltabel om veel-op-veel relatie te implementeren
CREATE TABLE schuldeiser_dossier (
    schuldeiser_id CHAR(36) NOT NULL COMMENT 'Referentie van een schuldeiser die aan een bepaald dossier is verbonden.',
    dossier_id CHAR(36) NOT NULL COMMENT 'Referentie van een dossier waaraan een bepaalde schuldeiser is verbonden.',

    FOREIGN KEY (schuldeiser_id) REFERENCES schuldeiser(id),
    FOREIGN KEY (dossier_id) REFERENCES dossier(id),

    INDEX idx_schuldeiser_dossier(schuldeiser_id),
    INDEX idx_dossier_schuldeiser(dossier_id)

) COMMENT 'Koppeltabel die de veel-op-veel relatie tussen dossiers en schuldeisers implementeerd.';

-- Budget
ALTER TABLE budget
    ADD COLUMN dossier_id CHAR(36) NOT NULL COMMENT 'Referentie van het dossier waaronder het budget is aangemaakt',
    ADD FOREIGN KEY (dossier_id) REFERENCES dossier(id),
    ADD INDEX idx_budget_dossier_id(dossier_id);

-- Budget
ALTER TABLE schuldeiser
    ADD COLUMN dossier_id CHAR(36) NOT NULL COMMENT 'Referentie van het dossier waaronder het budget is aangemaakt',
    ADD FOREIGN KEY (dossier_id) REFERENCES dossier(id),
    ADD INDEX idx_schuldeiser_dossier_id(dossier_id);
