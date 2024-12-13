package com.testtask.usersservicev1.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@SpringBootTest(
        properties = {
                "POSTGRES_DB_URL=jdbc:postgresql://localhost:5432/users-db",
                "MYSQL_DB_URL=jdbc:mysql://localhost:3306/users-database"
        })
@AutoConfigureMockMvc
@Testcontainers
abstract class AbstractIntegrationTest {

    private static final String DOCKER_COMPOSE_TEST_FILE = "src/test/resources/docker-compose-test.yml";
    private static final String POSTGRES_READINESS_MESSAGE_REGX = ".*database system is ready to accept connections.*";
    private static final String MYSQL_READINESS_MESSAGE_REGX = ".*/usr/sbin/mysqld: ready for connections..*";

    @Container
    static ComposeContainer composeContainer =
            new ComposeContainer(new File(DOCKER_COMPOSE_TEST_FILE))
                    .waitingFor("postgres-db", Wait.forLogMessage(POSTGRES_READINESS_MESSAGE_REGX, 2))
                    .waitingFor("mysql-db", Wait.forLogMessage(MYSQL_READINESS_MESSAGE_REGX, 2));
}
