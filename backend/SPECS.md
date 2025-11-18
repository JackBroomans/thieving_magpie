# Enumerations
## Code systems
1. The ***Ladis*** (IVZ) code system.\
This code system provides the codes and the JSON-parsing to deliver the general statistical data to this governmental
institution.
2. The ***Nen 7513*** code system.\
This code system provides the codes which are required for the logging of the use of the personal information which is
applicable to the 'AVG'.

Both of the code systems use the same format in code entities as well in database storage. This makes them applicable
to generic (shared) methods.

To save space in the database storage, the use of 'database enumerator types' or descriptive names is avoided. 
The database only stores a code attribute of the enumerator. This guarantees a compact database storage while in the 
backend and frontend the enumerators are applied.
### Code formats
Enumerators subjected to one of the code systems, must implement the 'SelectableCode' interface to ensure they commit 
the correct (unified) format and available generic methods.

Most enumerators implement an interface 'SelectableCode' which prescribes implementation of mandatory fields 
and methods:
- **number**: *Field - int* The key value which is used as identifier of the constant in de database.
- **code**: *Field - String* A code for the constant in the enumerator, specially used in code-systems.
- **display**: *Field - String* A short description of the constant, mostly used as label.
- **isActive**: *Field - boolean* Indicates if the constant is active or not.
- **isDefault**: *Field - boolean* Indicates if the constant is set as the default constant of a particular enumerator.
- **selectByKey()**: *Method* Selects a constant of a given enumerator based on the provided number (key value).
- **selectByCode()**: *Method* Selects a constant of a given enumerator based on the provided code.
- **selectDefault()**: *Method* Selects the default set constant of a particular enumerator.
#### The (general) code
The ladis codes are unique identificational by their table name and field number. Since other systems 
(periphery or integral) are not aware of this identification the additional *code* field, containing a unique code, 
is introduced to make the code interoperable to all other systems. The *code* attribute has the following structure:
1. Two (uppercase) characters which identifies the table (e.g. AD for AddictionDuration)
2. A hyphen (-)
3. A string of four digits in accordance with the Ladis number. (e.g. 0020)
Example; the code 'GL-0012' refers to favorite gambling location (GL) number 12, which is Internet.
### Ladis (EVZ)
The governmental 'Landelijk Alcohol and Drugs Informatie Systeem' (Ladis) is maintained by the 
'Stichting Informatievoorziening Zorg' (IVZ) on behalf of the 'Ministerie van Volksgezondheid'.\
The main purposes of Ladis is collecting, precessing and publishing figures concerning the ambulant- and clinical
mental healthcare practice. Since all GGZ-institutions and aid workers in mental health care are subjected to provide
their relevant data, IVZ maintains standards to format them, so they can be processed easily.\
Part of this standard isw the code system, which is implemented in the following tables:
- AD - AddictionDuration (Duur van de problematiek)
- AT - AddictionType (Primaire/Secundaire problematiek)*
- CT - ClientType (Soort client)
- (Doorverwezen door)
- (Burgerlijke staat)
- GN - (Geslacht)**
- (Psychiatrische problematiek)
- (Doel bereikt)
- (Eerdere behandeling in de verslavingszorg)
- (Gebruik middelen)
- (Prognose middelen gebruik)
- (Reden uitschrijving)
- (Setting)
- (Werkervaring)
- ED - Education (Opleidingsniveau afgerond)
- GL - GamblingLocation (Favoriete plaats om te gokken)
- GS - GeneralStateClient (Algemene toestand client)
- IP - InjectionPractice (Spuiten)
- LS - LivingSituation (Samenleefsituatie)
- MU - MethodOfUse (Wijze van gebruikt)
- SI - SourceOfIncome (Bron van inkomsten)
- TR - TypeOfResidence (Verblijfslocatie)
- UF - UsageFrequency (Frequentie van gebruikt)

> **Gender** is a general enumeration, thus not used by Ladis only. However, the format of the enumeration is compliant 
> with the Ladis format.\
> 
> **Primary en secondary addiction types** are two tables which are implemented in relation to each other. This means
> that secondary addiction types are subtypes of (exact) one particular primary type.

### Nen 7513

## Persisting enumerators
The standard options to persist enumerators (or their referrers) with Hibernate (JPA, Spring Boot), do have their 
drawbacks:
1. Using the ordinal number makes the relational integrity of the persisted data vulnerable the adding, removing or 
change sequence in the enumerator.
2. Using the name of the constant, costs a lot of (database) space and decreases the search performance, especially
when the amount  of record raises.

To avoid these drawbacks, the '*keyValue*' (named Number) attribute is introduced, which is stored as a small-integer
in the database and is independent of the ordinal order of the constants.
This practice, however, needs a method which search and select the appropriate constant based on this '*keyvalue*'.
This method 'getByKeyValue()' is a component of the '*EnumUtilities*' class.
### Post load and pre-persist methodology
The enumerators ar stored by reference with their '*key value*' (number) attributes, to realize a compact storage, 
while the enumerators and their constants ae  used in the backend code for clarity.
This means that both have to be synchronized always:
1. Before persisting to the database, to avoid selecting a constant from an enumerator while the (seperated)
   '*key values*'-field contains a previous one that will be persisted.
2. When fetching the persisted '*key value*' from the database.
   Synchronization is forced in both situations by the '*@PostLoad*' and '*@PrePersist*' annotations.
> Note that these mechanisms only work when '***Entity Projection***' is applied. See

# Database
The application is build against a PostgreSQL database.
## Local test and development databases
The development (and test) environment the (PostgreSQL) database is running is a Docker container, and the appropriate
Database connection is established from the used IDE.
The properties of this (development and test) database are:
- **Name**: thieving_magpie
- **Driver**: Postgres
- **Host**: localhost
- **Port**: 5432
- **User**: postgres
- **Password**: postgres
- **Database**:Postgres
- **URL**:jdbc:postgresql://localhost:5432/postgres
> Make sure the Docker deamon is running on your local device, either started at startup up, from the Docker desktop
> application or by the command:\
> *docker run -h thieving_magpie --name thieving_magpie -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres*
## Database schemes
The 'thieving_magpie' database contains the following database schemes:
1. **annoying_raven** The schema which holds the operational data.
2. **misconducting_owl** The schema which holds the data for the NEN7513 data
## Database structure changes (Migrations)
If, during development and/or maintenance changes on the database (schemes) structures are required, Flyway database
migrations **must** be applied to ensure the data structure integrity.
The versioning practice of the migrations follows the '***Vyyyymmddhhmmss__Vn__decription***' convention.
## JPA-2 implementation
As a persistence tool, 'Spring Boot Data', which includes Hibernate as JPA-2 implementation, is the leading practice
of the data persistence practice.
## Projection
Both common projection patterns will be used, each for their own strength and drawbacks:
1. The 'Entity Persistence Pattern' is used for the all write, update en delete operations, because the state is
managed by Hibernate, which makes the use of this pattern very convenient.
2. The 'Data Transfer Objects (DTO's)' are used for read-only operation, because managing states is not required and 
Hibernate doesm't need to perform any checks on 'dirty' data elements. Applying DTO's is much more efficient.



