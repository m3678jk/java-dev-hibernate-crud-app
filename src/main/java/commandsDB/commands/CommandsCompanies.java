package commandsDB.commands;

import commandsDB.entity.Company;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CommandsCompanies {
    HibernateUtil util = HibernateUtil.getInstance();

    public Company getById(long id) {
        Session session = util.getSessionFactory().openSession();
        Company company = session.get(Company.class, id);
        session.close();
        return company;
    }

    public List<Company> getList() {
        Session session = util.getSessionFactory().openSession();
        List<Company> companies = session.createQuery("from Company", Company.class).list(); // important to have a Capital letter
        session.close();
        return companies;
    }

    public void delete(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Company companyToDelete = getById(id);
        if (companyToDelete!=null) {
            session.remove(companyToDelete);
        } else {
            System.out.println("ID not found");
        }
        transaction.commit();
        session.close();
    }

    public Company inset(String name, String address) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Company company = new Company();
        company.setNameOfCompany(name);
        company.setAddress(address);
        session.persist(company);
        transaction.commit();
        session.close();
        return company;
    }

    public Company update(long id, String name, String address) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Company existing = session.get(Company.class, id);
        existing.setNameOfCompany(name);
        existing.setAddress(address);
        session.merge(existing);
        transaction.commit();
        session.close();
        return existing;
    }
}
