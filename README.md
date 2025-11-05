# Database
The application is build against a PostgreSQL database.
## Local test and development databases
The development (and test) environment the (PostgreSQL) database is running is a Docker container, and the appropriate Database connection is established from the used IDE.
The properties of this (development and test) database are:
- **Name**: thieving_magpie
- **Driver**: Postgres
- **Host**: localhost
- **Port**: 5432
- **User**: postgres
- **Password**: postgres
- **Database**:Postgres
- **URL**:jdbc:postgresql://localhost:5432/postgres
> Make sure the Docker deamon is running on your local device, either started at startup up, from the Docker desktop application or by the command: *docker run -h thieving_magpie --name thieving_magpie -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres*
## Database schemes
The 'thieving_magpie' database contains the following database schemes:
1. **annoying_raven** The schema which holds the operational data.
2. **misconducting_owl** The schema which holds the data for the NEN7513 data
## Database structure changes (Migrations)
If, during development and/or maintenance changes on the database (schemes) structures are required, Flyway database migrations **must** be applied to ensure the data structure integrity.
The versioning practice of the migrations follows the '***Vyyyymmddhhmmss__Vn__decription***' convention.
## JPA-2 implementation
As a persistence tool, 'Spring Boot Data', which includes Hibernate as JPA-2 implementation, is the leading practice of the data persistence practice.
## Projection
Both common projection patterns will be used, each for their own strength and drawbacks:
1. The 'Entity Persistence Pattern' is used for the all write, update en delete operations, because the state is managed by Hibernate, which makes the use of this pattern very convenient.
2. The 'Data Transfer Objects (DTO's)' are used for read-only operation, because managing states is not required and Hibernate doesm't need to perform any checks on 'dirty' data elements. Applying DTO's is much more efficient.
