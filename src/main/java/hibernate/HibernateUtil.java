package hibernate;

import serviceDAO.DAO.CompaniesDAO;
import serviceDAO.entity.*;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final HibernateUtil INSTANCE;
    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil(); // there is another way that singleton
    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Developer.class)
                .addAnnotatedClass(Company.class)
                .addAnnotatedClass(Skills.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Customer.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

    public static void main(String[] args) {
        CompaniesDAO companiesDAO = new CompaniesDAO();
//        System.out.println("companiesDAO.getById(1l) = " + companiesDAO.getById(1l));

       // System.out.println("companiesDAO.inset(\"new name\", \"address\") = " + companiesDAO.inset("new name", "address"));
       // System.out.println("companiesDAO.getList() = " + companiesDAO.getList());
        Company company =  new Company();
        company.setAddress("changed");
        company.setNameOfCompany("chnhed");
        companiesDAO.update(5L, company);
        System.out.println("companiesDAO.getList() = " + companiesDAO.getList());

//        ProjectDAO project = new ProjectDAO();
    }

}
