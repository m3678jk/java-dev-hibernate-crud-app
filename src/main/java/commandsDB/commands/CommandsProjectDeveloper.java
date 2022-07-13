package commandsDB.commands;

import commandsDB.entity.Developer;
import hibernate.HibernateUtil;
import org.hibernate.Session;


public class CommandsProjectDeveloper  {
    private HibernateUtil util = HibernateUtil.getInstance();

//
//    public void getById(long id_dev) {
//        Session session = util.getSessionFactory().openSession();
//        Developer developer = session.get(Developer.class, id);
//        session.close();
//        return developer;
//    }

    public static void main(String[] args) {
        HibernateUtil util = HibernateUtil.getInstance();
    }
}
