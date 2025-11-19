# Architectural principles
## Persistence
### Projections
Both common techniques of projection in the Spring Boot Framework are applied:
- Entity projection by Hibernate JPA2 implementation, mailnly used for Inserts, Updates and Deletions
- DTO projection by Spring Boot Data lkibrary implemenmtation, mainly used for Querying
### Database
During development a Dockerized Postgres database (container) is used. This means that the Docker engine must be running before the application can use this container.
### Database updates
To synchronize the database structures (DDL) during development and unit testeing, flyway migration on the Docker container are used.
### Enum persistence
All enumerator constants are provided with a '***key value***' attribute. This attribute is stored in the corresponding database field, providing;
1. Compact storage in the database.
2. Fully available enumerator functionality and ease of use in the backend.
### Post load and pre-persist mechanism
The post load (annotated) functionality is used to synchronize the enumerator constants according to the fetched '***key value***'. The enumerators themselves are transient, as mentioned.
On the other hand the pre-persist functionaly offer next to the required synchronization of enumerators, data validation checks. These checks however don't replace nor extends the '**Check Constraints**' on the database tables.
