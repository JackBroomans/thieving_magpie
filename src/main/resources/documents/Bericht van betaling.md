## Bericht van Betaling

### DTO mapping frontend

```
id                                                      verplicht   uniek   PK
status                                                  verplicht
betaling_idbetaling                                     verplicht           FK
dossier_id              betaling.dossier_id             verplicht           FK
dossier_naam            dossier_naam                    verplicht
bedrag                  betaling.bedrag                 verplicht
omschrijving            betaling.omschrijving           verplicht
betaald_op              betaling.betaald_op             verplicht
budget_id               betaling.budget_id                                  FK
schuldeiser_id          betaling.schuldeiser_id         verplicht
schuldeiser_naam        schuldeiser.naam                verplicht
afschijving_rekening    schuldeiser.bankrekeningnummer  verplicht
verzonden_op                                            verplicht
gelezen_op
gearchiveerd_op
```
