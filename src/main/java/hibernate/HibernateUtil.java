package hibernate;

import commandsDB.commands.CommandsCompanies;
import commandsDB.commands.CommandsProject;
import commandsDB.entity.Company;
import commandsDB.entity.Developer;
import commandsDB.entity.Project;
import commandsDB.entity.Skills;
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
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

    public static void main(String[] args) {
        CommandsCompanies commandsCompanies = new CommandsCompanies();
//        System.out.println("commandsCompanies.getById(1l) = " + commandsCompanies.getById(1l));

       // System.out.println("commandsCompanies.inset(\"new name\", \"address\") = " + commandsCompanies.inset("new name", "address"));
        System.out.println("commandsCompanies.getList() = " + commandsCompanies.getList());
        commandsCompanies.update(4L, "new modified", "new  modify address");
        System.out.println("commandsCompanies.getList() = " + commandsCompanies.getList());

        CommandsProject project = new CommandsProject();
    }

}
