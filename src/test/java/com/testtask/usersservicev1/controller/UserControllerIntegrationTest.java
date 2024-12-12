package com.testtask.usersservicev1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerIntegrationTest extends AbstractIntegrationTest {

    static final String QUERY_PARAM = "query";

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldFetchAllUsersFromAllDatabases() throws Exception {
        Path responseFilePath = Paths.get("src", "test", "resources", "expected-response", "all-users.json");
        String response = new String(Files.readAllBytes(Paths.get(responseFilePath.toUri())));
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(response));
    }

    @Test
    void shouldFetchUsersByName() throws Exception {
        Path responseFilePath = Paths.get("src", "test", "resources", "expected-response", "users-filter-by-name.json");
        String response = new String(Files.readAllBytes(Paths.get(responseFilePath.toUri())));
        String queryValue = "name=Diana";
        mockMvc.perform(get("/users")
                        .param(QUERY_PARAM, queryValue)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(response));
    }

    @Test
    void shouldFetchUsersByNameAndId() throws Exception {
        Path responseFilePath = Paths.get("src", "test", "resources", "expected-response", "users-filter-by-name-and-id.json");
        String response = new String(Files.readAllBytes(Paths.get(responseFilePath.toUri())));
        String queryValue = "name=Diana&id=6";
        mockMvc.perform(get("/users")
                        .param(QUERY_PARAM, queryValue)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(response));
    }
}
