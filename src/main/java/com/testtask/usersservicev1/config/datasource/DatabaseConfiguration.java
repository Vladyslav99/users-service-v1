package com.testtask.usersservicev1.config.datasource;

import com.testtask.usersservicev1.database.fetcher.UserFetcher;
import com.testtask.usersservicev1.database.fetcher.impl.UserFetcherImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public List<UserFetcher> usersFetchers(DataSourceProperties dataSourceProperties) {
        List<UserFetcher> userFetchers = new ArrayList<>();

        List<DataSourceProperties.Properties> properties = dataSourceProperties.getDataSourceProperties();
        properties.forEach(config -> {
            DataSource dataSource = createDataSource(config);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            UserFetcher userFetcher = new UserFetcherImpl(jdbcTemplate, config);
            userFetchers.add(userFetcher);
        });

        return userFetchers;
    }

    private DataSource createDataSource(DataSourceProperties.Properties properties) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getUrl());
        hikariConfig.setUsername(properties.getUser());
        hikariConfig.setPassword(properties.getPassword());
        hikariConfig.setDriverClassName(properties.getDriverClassName());

        return new HikariDataSource(hikariConfig);
    }
}
