package com.testtask.usersservicev1;

import org.springframework.boot.SpringApplication;

public class TestUsersServiceV1Application {

    public static void main(String[] args) {
        SpringApplication.from(UsersServiceV1Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
