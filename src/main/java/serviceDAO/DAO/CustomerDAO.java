package serviceDAO.DAO;

import serviceDAO.entity.Customer;
import hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDAO {
    HibernateUtil util = HibernateUtil.getInstance();

    public Customer getById(long id) {
        Session session = util.getSessionFactory().openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    public List<Customer> getList() {
        Session session = util.getSessionFactory().openSession();
        List<Customer> customers = session.createQuery("from Customer", Customer.class).list(); // important to have a Capital letter
        session.close();
        return customers;
    }

    public void delete(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Customer customerToDelete = getById(id);
        if (customerToDelete != null) {
            session.remove(customerToDelete);
        } else {
            System.out.println("ID not found");
        }
        transaction.commit();
        session.close();
    }

    public Customer inset(String name, String address) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        session.persist(customer);
        transaction.commit();
        session.close();


        return customer;
    }

    public Customer update(long id, String name, String address) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Customer existing = session.get(Customer.class, id);
        existing.setName(name);
        existing.setAddress(address);
        session.merge(existing);
        transaction.commit();
        session.close();
        return existing;
    }

}
