package com.testtask.usersservicev1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class UsersServiceV1ApplicationTests {

    @Test
    void contextLoads() {
    }

}
