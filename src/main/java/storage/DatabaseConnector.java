package storage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static final String DB_JDBC_CONNECTION_URL ="jdbc:mysql://127.0.0.1:3306/home_work_3";
    public static final String DB_JDBC_USER = "root";
    public static final String DB_JDBC_PASSWORD = "1234";
    private static final DatabaseConnector DATABASE_CONNECTOR = new DatabaseConnector();
    private Connection connection;


    private DatabaseConnector() {
        try {
            connection = DriverManager.getConnection(DB_JDBC_CONNECTION_URL, DB_JDBC_USER, DB_JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnector getDatabaseConnector() {
        return DATABASE_CONNECTOR;
    }


    public Connection getConnection() {
        return connection;
    }
}
