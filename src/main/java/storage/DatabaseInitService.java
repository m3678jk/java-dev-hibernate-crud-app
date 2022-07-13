package storage;

import org.flywaydb.core.Flyway;

import static storage.DatabaseConnector.*;

public class DatabaseInitService {

    public void initDB(DatabaseConnector databaseConnector){

        Flyway flyway = Flyway
                .configure()
                .dataSource(DB_JDBC_CONNECTION_URL, DB_JDBC_USER, DB_JDBC_PASSWORD).load();

        // Start the migration
        flyway.migrate();
    }
    }

