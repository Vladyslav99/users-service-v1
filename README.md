# API Docs

-----

This is RESTful API application for aggregating users from all configured databases in data-sources.yaml.

-----

## Run the app

### Prerequisite

1. Install **3.x**, **Java 17**, **docker** and **docker compose**.
2. Replace all `FULL_PATH_TO_THE_PROJECT` in `docker-compose-test.yml` and `docker-compose.yml` with full path to you
   project.
   Example `/home/my-user/IdeaProjects/users-service-v1`.

### Start the app using terminal

To start the app execute:

    mvn clean install && docker build -t users-service-v1:latest .
    docker compose up -d

After start up the application is available under:

`http://localhost:8080/geometric-shapes-service-v1/api/v1`

To stop and clean up the app execute:

    docker compose down

### Start the app using Intellij Idea

To start dbs execute:

    docker compose up --scale users-service-v1=0 -d

To start the app you can use UsersServiceV1Application.java as entrypoint.
Configure the following ENV variables in your Run/Debug Configurations:

    MYSQL_DB_URL=jdbc:mysql://localhost:3306/users-database;
    POSTGRES_DB_URL=jdbc:postgresql://localhost:5432/users-db

-----

# REST API

OpenApi Docs are available under the following url after application start up:

`http://localhost:8080/users-service-v1/api/v1/swagger-ui/index.html`