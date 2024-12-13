package com.testtask.usersservicev1.database.fetcher.impl;

import com.testtask.usersservicev1.config.datasource.DataSourceProperties;
import com.testtask.usersservicev1.utils.Filters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.HashMap;
import java.util.Map;

import static com.testtask.usersservicev1.utils.USERS_TABLE.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserFetcherImplTest {

    static final String FIRST_NAME = "john";

    static final String USERS_QUERY = String.format("SELECT %s, %s, %s, %s FROM %s WHERE 1 = 1",
            COLUMN_ID, COLUMN_USERNAME, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, USERS_TABLE);
    static final String USERS_QUERY_BY_NAME = String.format("SELECT %s, %s, %s, %s FROM %s WHERE 1 = 1 AND %s = '%s'",
            COLUMN_ID, COLUMN_USERNAME, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, USERS_TABLE, COLUMN_FIRST_NAME, FIRST_NAME);

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    DataSourceProperties.Properties properties;

    @Mock
    DataSourceProperties.Properties.Mapping mapping;

    @InjectMocks
    UserFetcherImpl testedInstance;

    @BeforeEach
    void setUp() {
        when(properties.getMapping()).thenReturn(mapping);
        when(properties.getTable()).thenReturn(USERS_TABLE);
        when(mapping.getId()).thenReturn(COLUMN_ID);
        when(mapping.getUsername()).thenReturn(COLUMN_USERNAME);
        when(mapping.getName()).thenReturn(COLUMN_FIRST_NAME);
        when(mapping.getSurname()).thenReturn(COLUMN_LAST_NAME);
    }

    @Test
    void shouldFetchAllUsers() {
        testedInstance.fetch(new HashMap<>());

        verify(jdbcTemplate).query(eq(USERS_QUERY), any(RowMapper.class));
    }

    @Test
    void shouldFetchUsersByName() {
        testedInstance.fetch(Map.of(Filters.NAME, FIRST_NAME));

        verify(jdbcTemplate).query(eq(USERS_QUERY_BY_NAME), any(RowMapper.class));
    }

}