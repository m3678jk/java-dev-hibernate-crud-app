import storage.DatabaseConnector;
import storage.DatabaseInitService;

public class App {
    public static void main(String[] args) {
        DatabaseConnector databaseConnector = DatabaseConnector.getDatabaseConnector();
        new DatabaseInitService().initDB(databaseConnector);
    }
}
