package commandsDB.commands;

import commandsDB.entity.Skills;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CommandsSkills {
    HibernateUtil util = HibernateUtil.getInstance();

    public Skills getById(long id) {
        Session session = util.getSessionFactory().openSession();
        Skills skills = session.get(Skills.class, id);
        session.close();
        return skills;
    }

    public List<Skills> getList() {
        Session session = util.getSessionFactory().openSession();
        List<Skills> skills = session.createQuery("from Skills", Skills.class).list();
        session.close();
        return skills;
    }

    public void delete(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Skills skillsToDelete = getById(id);
        if (skillsToDelete!=null) {
            session.remove(skillsToDelete);
        } else {
            System.out.println("ID not found");
        }
        transaction.commit();
        session.close();
    }


    public Skills inset(long idDev, Skills.Technology technology, String levelOfPosition) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Skills skills = new Skills();
        skills.setIdDev(idDev);
        skills.setTechnology(technology);
        skills.setLevelOfPosition(levelOfPosition);
        session.persist(skills);
        transaction.commit();
        session.close();
        return skills;
    }

    public Skills update(long id, long idDev, Skills.Technology technology, String levelOfPosition) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Skills existingSkills = session.get(Skills.class, id);
        existingSkills.setIdDev(idDev);
        existingSkills.setTechnology(technology);
        existingSkills.setLevelOfPosition(levelOfPosition);
        session.merge(existingSkills);
        transaction.commit();
        session.close();
        return existingSkills;
    }


    public static void main(String[] args) {
        CommandsSkills commandsSkills = new CommandsSkills();
        System.out.println("commandsSkills.getById(1l) = " + commandsSkills.getById(10l));
//        System.out.println("commandsSkills.inset(2L, Skills.Technology.Java, \"middle\") = " + commandsSkills.inset(2L, Skills.Technology.Java, "middle"));
//        System.out.println("commandsSkills.getList() = " + commandsSkills.getList());
//        System.out.println("commandsSkills.update(10, 4, Skills.Technology.Java, \"middle\") = " + commandsSkills.update(10, 4, Skills.Technology.Java, "middle"));
//        System.out.println("commandsSkills.getList() = " + commandsSkills.getList());

    }

}