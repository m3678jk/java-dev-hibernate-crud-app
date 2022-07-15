package serviceDAO.manager;

import lombok.Getter;
import serviceDAO.DAO.*;
import storage.DatabaseConnector;
import storage.DatabaseInitService;


public class DatabaseManager {

    private final DatabaseConnector databaseConnector;
    @Getter
    private  RelationManager relationManager;
    @Getter
    private  OperationOnDB operationOnDB;
    @Getter
    private  ProjectDAO projectDAO;
    @Getter
    private  DeveloperDAO developerDAO;
    @Getter
    private  CustomerDAO customerDAO;
    @Getter
    private  CompaniesDAO companiesDAO;
    @Getter
    private SkillsDAO skillsDAO;

    public DatabaseManager() {
        databaseConnector = DatabaseConnector.getDatabaseConnector();
        new DatabaseInitService().initDB(databaseConnector);
        relationManager = new RelationManager();
        operationOnDB = new OperationOnDB();
        projectDAO = new ProjectDAO();
        developerDAO = new DeveloperDAO();
        customerDAO = new CustomerDAO();
        companiesDAO = new CompaniesDAO();
        skillsDAO = new SkillsDAO();
    }
}
