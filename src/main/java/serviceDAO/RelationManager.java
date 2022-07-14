package serviceDAO;

import serviceDAO.DAO.CompaniesDAO;
import serviceDAO.DAO.CustomerDAO;
import serviceDAO.DAO.DeveloperDAO;
import serviceDAO.DAO.ProjectDAO;
import serviceDAO.entity.Company;
import serviceDAO.entity.Customer;
import serviceDAO.entity.Developer;
import serviceDAO.entity.Project;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RelationManager {

    private HibernateUtil util = HibernateUtil.getInstance();

    private final ProjectDAO projectDAO;
    private final DeveloperDAO developerDAO;
    private final CustomerDAO customerDAO;
    private final CompaniesDAO companiesDAO;


    public RelationManager() {
        projectDAO = new ProjectDAO();
        developerDAO = new DeveloperDAO();
        customerDAO = new CustomerDAO();
        companiesDAO = new CompaniesDAO();
    }

    public void addDeveloperToProject(long projectId, long developerId) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Project project = projectDAO.getById(projectId);
        Developer developer = developerDAO.getById(developerId);
        if (project != null && developer != null) {
            project.addDeveloper(developer);
            session.merge(project);

        } else {
            System.out.println("ID incorrect");
        }
        transaction.commit();
        session.close();
    }

    public void deleteDeveloperFromProject(long projectId, long developerId) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Project project = projectDAO.getById(projectId);

        Developer developer = developerDAO.getById(developerId);
        if (project != null && developer != null) {
            project.removeDeveloper(developer);
            session.merge(project);
        } else {
            System.out.println("ID incorrect");
        }
        transaction.commit();
        session.close();
    }

    public void addCustomerToProject(long projectId, long customerId) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Project project = projectDAO.getById(projectId);
        Customer customer = customerDAO.getById(customerId);
        if (project != null && customer != null) {
            project.addCustomer(customer);
            session.merge(project);

        } else {
            System.out.println("ID incorrect");
        }
        transaction.commit();
        session.close();
    }

    public void deleteCustomerFromProject(long projectId, long companyId) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Project project = projectDAO.getById(projectId);
        Customer customer = customerDAO.getById(companyId);
        if (project != null && customer != null) {
            project.removeCustomer(customer);
            session.merge(project);
        } else {
            System.out.println("ID incorrect");
        }
        transaction.commit();
        session.close();
    }

    public void addCompanyToProject(long projectId, long companyId) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Project project = projectDAO.getById(projectId);
        Company company = companiesDAO.getById(companyId);
        if (project != null && company != null) {
            project.addCompany(company);
            session.merge(project);

        } else {
            System.out.println("ID incorrect");
        }
        transaction.commit();
        session.close();
    }

    public void deleteCompanyFromProject(long projectId, long customerId) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Project project = projectDAO.getById(projectId);
        Company company = companiesDAO.getById(customerId);
        if (project != null && company != null) {
            project.removeCompany(company);
            session.merge(project);
        } else {
            System.out.println("ID incorrect");
        }
        transaction.commit();
        session.close();
    }

    public static void main(String[] args) {
        RelationManager relationManager = new RelationManager();
       // relationManager.addCompanyToProject(1, 2);
        relationManager.addDeveloperToProject(4, 1);
        relationManager.addDeveloperToProject(4, 3);
        relationManager.addDeveloperToProject(4, 6);


    }

}
