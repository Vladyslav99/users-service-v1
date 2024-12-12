package com.testtask.usersservicev1.database.fetcher.impl;

import com.testtask.usersservicev1.config.datasource.DataSourceProperties;
import com.testtask.usersservicev1.database.entity.User;
import com.testtask.usersservicev1.database.fetcher.UserFetcher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class UserFetcherImpl implements UserFetcher {

    private static final String USERS_QUERY = "SELECT %s, %s, %s, %s FROM %s WHERE 1 = 1";

    private final JdbcTemplate jdbcTemplate;

    private final DataSourceProperties.Properties properties;

    public UserFetcherImpl(JdbcTemplate jdbcTemplate, DataSourceProperties.Properties properties) {
        this.jdbcTemplate = jdbcTemplate;
        this.properties = properties;
    }

    @Override
    public List<User> fetch(Map<String, String> filters) {
        DataSourceProperties.Properties.Mapping mapping = properties.getMapping();
        // Should be analyzed how to rewrite to avoid potential sql injections
        String query = String.format(USERS_QUERY, mapping.getId(), mapping.getUsername(), mapping.getName(),
                mapping.getSurname(), properties.getTable());

        String formattedQuery = formatQuery(query, filters);

        return jdbcTemplate.query(formattedQuery, (rs, rowNum) -> new User(rs.getString(mapping.getId()),
                rs.getString(mapping.getUsername()),
                rs.getString(mapping.getName()),
                rs.getString(mapping.getSurname()))
        );
    }

    private String formatQuery(String query, Map<String, String> filters) {
        StringBuilder builder = new StringBuilder(query);

        filters.forEach((key, value) -> {
            String column = extractColumn(key);
            if (!column.isEmpty()) {
                builder.append(String.format(" AND %s = '%s'", column, value));
            }
        });

        return builder.toString();
    }

    private String extractColumn(String filter) {
        DataSourceProperties.Properties.Mapping mapping = properties.getMapping();
        return switch (filter) {
            case "id" -> mapping.getId();
            case "username" -> mapping.getUsername();
            case "name" -> mapping.getName();
            case "surname" -> mapping.getSurname();
            default -> StringUtils.EMPTY;
        };
    }
}
