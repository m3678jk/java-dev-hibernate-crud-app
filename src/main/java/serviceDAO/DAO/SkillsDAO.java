package serviceDAO.DAO;

import serviceDAO.entity.Customer;
import serviceDAO.entity.Developer;
import serviceDAO.entity.Skills;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class SkillsDAO implements DAO{
    HibernateUtil util = HibernateUtil.getInstance();

    public Skills getById(long id) {
        Session session = util.getSessionFactory().openSession();
        Skills skills = session.get(Skills.class, id);
        session.close();
        return skills;
    }
    //TODO it was working then stopped. do not know why
    public List<Skills> getList() {
        Session session = util.getSessionFactory().openSession();
        List<Skills> skills = session.createQuery("from Skills as s inner join fetch s.developer", Skills.class).list();
        session.close();
        return skills;
    }

    public void delete(long skillsId) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Skills skillsToDelete = session.find(Skills.class, skillsId);
        if (skillsToDelete!=null) {
            session.remove(skillsToDelete);
        } else {
            System.out.println("ID not found");
        }
        transaction.commit();
        session.close();
    }

    public void insert(long developerId, Skills skills) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DeveloperDAO developerDAO = new DeveloperDAO();
        Developer developer = developerDAO.getById(developerId);
        List<Skills> collect = developer.getSkills().stream().filter(it -> it.equals(skills)).collect(Collectors.toList());
        if (!collect.isEmpty()){
            System.out.println("that skills are already exist");
        } else if (developer != null) {
            developer.addSkills(skills);
            session.merge(developer);
        } else {
            System.out.println("ID incorrect");
        }
        transaction.commit();
        session.close();
    }

    public void update(long skillsId, Skills skills) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Skills.Technology technology = skills.getTechnology();
        String levelOfPosition = skills.getLevelOfPosition();

        Skills existingSkills = getById(skillsId);

        if (existingSkills != null) {
            existingSkills.setTechnology(technology);
            existingSkills.setLevelOfPosition(levelOfPosition);
            session.merge(existingSkills);

        } else {
            System.out.println("ID incorrect");
        }
        transaction.commit();
        session.close();
    }


    public static void main(String[] args) {
        SkillsDAO skillsDAO = new SkillsDAO();

        Skills skills = new Skills();
        skills.setTechnology(Skills.Technology.Java);
        skills.setLevelOfPosition("Middle");

        Skills skills2 = new Skills();
        skills2.setTechnology(Skills.Technology.Java);
        skills2.setLevelOfPosition("Junior");

        Skills skills3 = new Skills();
        skills3.setTechnology(Skills.Technology.C_PLUS_PLUS);
        skills3.setLevelOfPosition("Junior");

        skillsDAO.insert(6, skills);
//        skillsDAO.update(3, skills2);
//        skillsDAO.update(5, skills3);
//        skillsDAO.update(7, skills);
//        skillsDAO.update(8, skills3);
//        skillsDAO.update(10, skills3);

//        System.out.println("skillsDAO.getById(1) = " + skillsDAO.getById(1));

//        System.out.println("skillsDAO.getList() = " + skillsDAO.getList());
    }

}