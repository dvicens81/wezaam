# wezaam

## Technologies
Project is created with:
* SpringBoot 2.5.2
* Maven
* Java 8
* H2 Embedded Database
* Junit 5

## Build and deploy
### Docker compose
```
docker-compose build
docker-compose up
```
```
mvn spring-boot:run
```
## Run test
```
mvn test
```
## Swagger
* http://localhost:8091/swagger-ui/

## Endpoints available
* Find all users (GET)
* Create withdrawal by user (POST)

### Solution
* I create a new project, because I consider that the actual solution needs a deeply refactor and it is better to start from scratch to refactor.
* The database used is H2 Embedded mode MYSQL.
* It is important to know that every time that the application is started, the database is empty.
* I add TODOS tags to take account the technical debt that are missig now.
* The part "losing some outgoing events about withdrawal..." is explained on EventsService.class
