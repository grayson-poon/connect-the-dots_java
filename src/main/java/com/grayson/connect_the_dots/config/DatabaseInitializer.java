package com.grayson.connect_the_dots.config;

import lombok.NoArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

@Configuration
@NoArgsConstructor
public class DatabaseInitializer {
    private final String databaseName = "connect_the_dots";

    @Value("${spring.datasource.host}")
    private String databaseHost;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Bean
    public ApplicationRunner initialize() {
        return args -> {
            Flyway flyway = this.configureFlyway();
            this.createDatabase();
            this.applyMigrations(flyway);
        };
    }

    private void createDatabase() {
        final String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + this.databaseName + ";";

        try (
            Connection connection = DriverManager.getConnection(databaseHost, databaseUsername, databasePassword);
            Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(createDatabaseQuery);
            System.out.println("Successfully executed the following query: \"" + createDatabaseQuery + "\"");
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }

    private Flyway configureFlyway() {
        return Flyway.configure()
                .dataSource(databaseHost, databaseUsername, databasePassword)
                .defaultSchema(this.databaseName)
                .baselineOnMigrate(true)
                .load();
    }

    private void applyMigrations(Flyway flyway) {
        try {
            flyway.migrate();
            System.out.println("Flyway applied migrations successfully.");
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
