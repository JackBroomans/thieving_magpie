# Client

## Beschrijving entiteit
1. **id** - Een unieke identificate van de client die als UUID door het systeem wordt uitgeven en wat als relatie-attribuut wordt gebruikt.
2. **clientnummer** - Het unieke clientnummer zoals dat wordt gebruikt als, min of meer leesbare versie van de primary key. Het clientnummer bestaat uit:
     * De eerste letter van de initialen (kapitaal)
     * De eerste twee letters van de achternaam (kapitalen)
     * Tien posities die het moment van opslaan representeren. Het formaat wordt geverifieerd met een 'regular expression' en is als volgt opgebouwd:
       * De minuten binnen het uur, waar nodig voorzien van voorloopnullen.
       * Het uur volgens de 24-uurs notatie, waar nodig voorzien van voorloopnul.
       * De dag, gerepresenteerd door twee cijfers, waar nodig voorzien van een voorloopnul.
       * De maand, gerepresenteerd door twee cijfer, waar nodig voorzien van een voorloopnul.
       * Het jaar gerepresenteerd door twee cijfers, dus zonder eeuw-aanduiding.
3. **familienaam** - De familienaam, indien van toepassing en gewenst inclusief de familienaam van de partner, volgens het door de cliënt gewenste naamgebruik, maar zonder de voorvoegsels van de eerste naam.
4. **voorvoegsels** - De bij de naam (eerst gekozen volgens het naamgebruik) behorende voorvoegsels.
5. **voorletters** - De voorletters van de cliënt.

## Tabeldefinitie
### Velden
1. **id**&ensp;string(36)&ensp;PK
2. **clientnummer**&ensp;string(14)&ensp;-> idx_client_clientnummer
3. **familienaam**&ensp;string(127)&ensp;-> idx_client_familienaam
4. **voorvoegsels**&ensp;string(31)
5. **voorletters**&ensp;string(31)
### Methoden
* **generateClientnummer()**

    Deze methode genereert een uniek clientnummer uit de componenten, zoals beschreven in de 'Beschijving entiteit' onder 'clientnummer'. Vervolgens controleert de methode of dit clientnummer uniek is. Indien dit het geval is dan zal de methode het clientnummer retourneren. Mocht dit niet het geval zijn dan wordt een 'IllegalArgumentException' gengenereed.
