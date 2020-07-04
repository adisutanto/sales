# Sales application

## Build and run

```shell script
./mvnw clean package
java -jar target/sales-0.0.1-SNAPSHOT.jar
```
By default, it runs with `local` profile, which initializes sample data.

## Design

### Functional Design

For the top selling products report, we limit result to top N (configurable), where N by default is 10.
Because users typically don't bother with non top-N. UI won't be crowded with too many results.

For the sales by month report, we limit result to the most recent N months (configurable), by default is 12 months.

### Non-Functional Design

#### Production-ready features

We use Spring Boot Actuator to enable production-ready features.
Features example: health check, monitoring, logging, metrics, etc.

#### High availability

This application could be deployed to any cloud platform, with at least 2 instances on different zones to achieve high availability.

#### Logging

Every important methods have logging.

#### UTF-8

Default encoding is utf-8.

### Technology

* Java
* Spring Boot
* H2
* Flyway
* Gradle

#### Automated tests

We don't provide any test cases for Spring Data REST features like CRUD, since it is a well-known library which has extensive automated tests.
We do provide automated tests for the custom API codes that we have written.
By using standard technology, our codebase becomes more concise and readable, as well as achieving business requirement. 

#### Database

For proof-of-concept, quick to start application, we use H2 database.
For production application, we may choose PostgreSQL or OracleDB.

#### Database Migration

We use Flyway for database source control.
