import serviceDAO.entity.Skills;
import serviceDAO.manager.DatabaseManager;

public class App {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        //There are 6 developers, 4 projects, 3 companies, 4 customers

        //=======
        // insert data to Skills table
        //=======

        Skills skills = new Skills();
        skills.setTechnology(Skills.Technology.C_SHARP);
        skills.setLevelOfPosition("Junior");

        Skills skills2 = new Skills();
        skills2.setTechnology(Skills.Technology.Java);
        skills2.setLevelOfPosition("Middle");

        Skills skills3 = new Skills();
        skills3.setTechnology(Skills.Technology.JS);
        skills3.setLevelOfPosition("Middle");

        Skills skills4 = new Skills();
        skills4.setTechnology(Skills.Technology.C_PLUS_PLUS);
        skills4.setLevelOfPosition("Senior");

        databaseManager.getSkillsDAO().insert(1, skills);
        databaseManager.getSkillsDAO().insert(2, skills3);
        databaseManager.getSkillsDAO().insert(4, skills2);
        databaseManager.getSkillsDAO().insert(3, skills2);
        databaseManager.getSkillsDAO().insert(5, skills4);
        databaseManager.getSkillsDAO().insert(6, skills);
        databaseManager.getSkillsDAO().insert(5, skills2);

        //=======
        // setting relation between projects, companies, developers, customers tabel
        //=======

        databaseManager.getRelationManager().addDeveloperToProject(1, 2);
        databaseManager.getRelationManager().addDeveloperToProject(3, 2);
        databaseManager.getRelationManager().addDeveloperToProject(2, 6);
        databaseManager.getRelationManager().addDeveloperToProject(4, 1);
        databaseManager.getRelationManager().addDeveloperToProject(4, 3);
        databaseManager.getRelationManager().addDeveloperToProject(1, 5);
        databaseManager.getRelationManager().addDeveloperToProject(2, 4);


        databaseManager.getRelationManager().addCompanyToProject(4, 1);
        databaseManager.getRelationManager().addCompanyToProject(3, 2);
        databaseManager.getRelationManager().addCompanyToProject(1, 3);
        databaseManager.getRelationManager().addCompanyToProject(2, 1);


        databaseManager.getRelationManager().addCustomerToProject(4,2);
        databaseManager.getRelationManager().addCustomerToProject(3,1);
        databaseManager.getRelationManager().addCustomerToProject(4,4);
        databaseManager.getRelationManager().addCustomerToProject(1,3);

        //=======
        // additional query (as it was in HW3)
        //=======


        System.out.println(databaseManager.getOperationOnDB().getListMidDev());
        System.out.println(databaseManager.getOperationOnDB().getListOfJavaDev());
        System.out.println(databaseManager.getOperationOnDB().getSumOfSalary(1));
        System.out.println(databaseManager.getOperationOnDB().getListOfDevelopers(4));
        System.out.println(databaseManager.getOperationOnDB().getListOfProject2());



    }
}
