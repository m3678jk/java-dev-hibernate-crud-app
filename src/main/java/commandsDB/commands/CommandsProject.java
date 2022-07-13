package commandsDB.commands;

import commandsDB.entity.Developer;
import commandsDB.entity.Project;
import hibernate.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class CommandsProject {
    private HibernateUtil util = HibernateUtil.getInstance();


    public Project getById(long id) {
        Session session = util.getSessionFactory().openSession();
        Project project = session.get(Project.class, id);
        session.close();
        return project;
    }

    public List<Project> getList() {
        Session session = util.getSessionFactory().openSession();
        List<Project> projects = session.createQuery("from Project", Project.class).list();
        session.close();
        return projects;
    }

    public void delete(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Project projectToDelete = getById(id);
        if (projectToDelete != null) {
            session.remove(projectToDelete);
        } else {
            System.out.println("ID not found");
        }
        transaction.commit();
        session.close();
    }

    //TODO
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

    //TODO
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


//    public void addDeveloperToProject(long projectId, long developerId) {
//        Session session = util.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Project project = getById(projectId);
//        CommandsDevelopers commandsDevelopers = new CommandsDevelopers();
//        Developer developer = commandsDevelopers.getById(developerId);
//        if (project != null && developer != null) {
//            project.addDeveloper(developer);
//            session.merge(project);
//        } else {
//            System.out.println("ID incorrect");
//        }
//        transaction.commit();
//        session.close();
//    }

//    public void deleteDeveloperFromProject(long projectId, long developerId){
//        Session session = util.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Project project = getById(projectId);
//        CommandsDevelopers commandsDevelopers = new CommandsDevelopers();
//        Developer developer = commandsDevelopers.getById(developerId);
//        if (project != null && developer != null) {
////            project.getDeveloperSet().remove(developer);
//           // developer.getProjectsSet().remove(project);
//            session.merge(project);
//        } else {
//            System.out.println("ID incorrect");
//        }
//        transaction.commit();
//        session.close();
//    }

    public static void main(String[] args) {
        CommandsProject commands = new CommandsProject();
         System.out.println("commandsDevelopers.get(1L) = " + commands.getById(1L));
        // System.out.println("commandsDevelopers.inset(\"Jhon\", \"Lee\", 33, Developer.Sex.male, 4000) = " + commandsDevelopers.inset("Jhon", "Lee", 33, Developer.Sex.male, 4000));
        // System.out.println("commandsDevelopers.update(7, \"Joanna\",\"Lee\" , 39, Developer.Sex.female, 7000) = " + commandsDevelopers.update(7, "Joanna", "Lee", 39, Developer.Sex.female, 7000));
        // System.out.println("commandsDevelopers.getList() = " + commandsDevelopers.getList());
      //  commands.addDeveloperToProject(2, 2);
//        commands.deleteDeveloperFromProject(4, 1);
        //commands.deleteDeveloperFromProject(1, 1);


        // Project project = new Project();
        // System.out.println("project.getDeveloperSet() = " + project.getDeveloperSet());
    }
}
