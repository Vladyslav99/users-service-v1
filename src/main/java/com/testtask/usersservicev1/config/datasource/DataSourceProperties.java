package com.testtask.usersservicev1.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConfigurationProperties("db-config")
public class DataSourceProperties {

    private List<Properties> dataSourceProperties;

    public List<Properties> getDataSourceProperties() {
        return dataSourceProperties;
    }

    public void setDataSourceProperties(List<Properties> dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    public static class Properties {

        private String name;

        private String strategy;

        private String driverClassName;

        private String url;

        private String table;

        private String user;

        private String password;

        private Mapping mapping;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Mapping getMapping() {
            return mapping;
        }

        public void setMapping(Mapping mapping) {
            this.mapping = mapping;
        }

        public static class Mapping {

            private String id;

            private String username;

            private String name;

            private String surname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSurname() {
                return surname;
            }

            public void setSurname(String surname) {
                this.surname = surname;
            }
        }
    }
}
