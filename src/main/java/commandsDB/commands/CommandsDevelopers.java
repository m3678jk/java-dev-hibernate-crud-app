package commandsDB.commands;

import commandsDB.entity.Company;
import commandsDB.entity.Developer;
import commandsDB.entity.Skills;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class CommandsDevelopers{
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

    public Developer inset(String firstName, String secondName, int age, Developer.Sex sex, int salary) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setSecondName(secondName);
        developer.setAge(age);
        developer.setSex(sex);
        developer.setSalary(salary);
        session.persist(developer);
        transaction.commit();
        session.close();
        return developer;
    }

    public Developer update(long id, String firstName, String secondName, int age, Developer.Sex sex, int salary) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Developer existingDeveloper = session.get(Developer.class, id);
        existingDeveloper.setFirstName(firstName);
        existingDeveloper.setSecondName(secondName);
        existingDeveloper.setAge(age);
        existingDeveloper.setSex(sex);
        existingDeveloper.setSalary(salary);
        session.merge(existingDeveloper);
        transaction.commit();
        session.close();
        return existingDeveloper;
    }

    public static void main(String[] args) {
        CommandsDevelopers commandsDevelopers =new CommandsDevelopers();
        //System.out.println("commandsDevelopers.get(1L) = " + commandsDevelopers.get(1L));
       // System.out.println("commandsDevelopers.inset(\"Jhon\", \"Lee\", 33, Developer.Sex.male, 4000) = " + commandsDevelopers.inset("Jhon", "Lee", 33, Developer.Sex.male, 4000));
       // System.out.println("commandsDevelopers.update(7, \"Joanna\",\"Lee\" , 39, Developer.Sex.female, 7000) = " + commandsDevelopers.update(7, "Joanna", "Lee", 39, Developer.Sex.female, 7000));
        System.out.println("commandsDevelopers.getList() = " + commandsDevelopers.getList());

    }
}



