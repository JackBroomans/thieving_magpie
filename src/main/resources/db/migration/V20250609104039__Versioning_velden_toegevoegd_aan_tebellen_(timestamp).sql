USE schuldhulp;

ALTER TABLE client ADD COLUMN version TIMESTAMP NOT NULL DEFAULT NOW() COMMENT 'Hibernate (JPA) veld wat de status van objecten bijhoudt, om optimistic locking in transacties af te handelen.';
ALTER TABLE betaling ADD COLUMN version TIMESTAMP NOT NULL DEFAULT NOW() COMMENT 'Hibernate (JPA) veld wat de status van objecten bijhoudt, om optimistic locking in transacties af te handelen.';
ALTER TABLE budget ADD COLUMN version TIMESTAMP NOT NULL DEFAULT NOW() COMMENT 'Hibernate (JPA) veld wat de status van objecten bijhoudt, om optimistic locking in transacties af te handelen.';
ALTER TABLE dossier ADD COLUMN version TIMESTAMP NOT NULL DEFAULT NOW() COMMENT 'Hibernate (JPA) veld wat de status van objecten bijhoudt, om optimistic locking in transacties af te handelen.';
ALTER TABLE schuldeiser ADD COLUMN version TIMESTAMP NOT NULL DEFAULT NOW() COMMENT 'Hibernate (JPA) veld wat de status van objecten bijhoudt, om optimistic locking in transacties af te handelen.';
