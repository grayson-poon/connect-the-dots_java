package com.grayson.connect_the_dots.config;

import lombok.NoArgsConstructor;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

@Configuration
@NoArgsConstructor
public class DatabaseInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

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

    private Flyway configureFlyway() {
        logger.info("Configuring Flyway.");

        return Flyway.configure()
                .dataSource(databaseHost, databaseUsername, databasePassword)
                .defaultSchema(this.databaseName)
                .baselineOnMigrate(true)
                .load();
    }

    private void createDatabase() {
        logger.info("Attempting to create the application database.");

        final String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + this.databaseName + ";";

        try (
            Connection connection = DriverManager.getConnection(databaseHost, databaseUsername, databasePassword);
            Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(createDatabaseQuery);
            logger.info("Successfully executed the following query: \"" + createDatabaseQuery + "\"");
        } catch (Exception error) {
            logger.error(error.getMessage());
        }
    }

    private void applyMigrations(Flyway flyway) {
        logger.info("Applying migrations manually.");

        try {
            flyway.migrate();
            logger.info("Flyway applied migrations successfully.");
        } catch (Exception error) {
            logger.error(error.getMessage());
        }
    }
}
