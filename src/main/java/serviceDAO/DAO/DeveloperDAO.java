package serviceDAO.DAO;

import serviceDAO.entity.Developer;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DeveloperDAO implements DAO{
   private HibernateUtil util = HibernateUtil.getInstance();


    public Developer getById(long id) {
        Session session = util.getSessionFactory().openSession();
        Developer developer = session.get(Developer.class, id);
        session.close();
        return developer;
    }

    public List<Developer> getList() {
        Session session = util.getSessionFactory().openSession();
        List<Developer> developers = session.createQuery("from Developer", Developer.class).list(); // important to have a Capital letter
        session.close();
        return developers;
    }

    public void delete(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Developer developerToDelete = getById(id);
        if (developerToDelete!=null) {
            session.remove(developerToDelete);
        } else {
            System.out.println("ID not found");
        }
        transaction.commit();
        session.close();
    }

    public void inset(Developer developer) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(developer);
        transaction.commit();
        session.close();
    }

    public void update(long id, Developer developer) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        int age = developer.getAge();
        String firstName = developer.getFirstName();
        String secondName = developer.getSecondName();
        int salary = developer.getSalary();
        Developer.Sex sex = developer.getSex();

        Developer existingDeveloper = session.get(Developer.class, id);
        if (existingDeveloper!=null) {
            existingDeveloper.setFirstName(firstName);
            existingDeveloper.setSecondName(secondName);
            existingDeveloper.setAge(age);
            existingDeveloper.setSex(sex);
            existingDeveloper.setSalary(salary);
            session.merge(existingDeveloper);
        } else {
            System.out.println("ID incorrect");
        }
        transaction.commit();
        session.close();
    }

    public static void main(String[] args) {
        DeveloperDAO developerDAO =new DeveloperDAO();
        //System.out.println("developerDAO.get(1L) = " + developerDAO.get(1L));
       // System.out.println("developerDAO.inset(\"Jhon\", \"Lee\", 33, Developer.Sex.male, 4000) = " + developerDAO.inset("Jhon", "Lee", 33, Developer.Sex.male, 4000));
       // System.out.println("developerDAO.update(7, \"Joanna\",\"Lee\" , 39, Developer.Sex.female, 7000) = " + developerDAO.update(7, "Joanna", "Lee", 39, Developer.Sex.female, 7000));
        System.out.println("developerDAO.getList() = " + developerDAO.getList());

    }
}



