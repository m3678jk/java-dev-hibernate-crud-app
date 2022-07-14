package serviceDAO;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.sql.internal.SQLQueryParser;
import serviceDAO.entity.Developer;
import serviceDAO.entity.Project;
import serviceDAO.entity.Skills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OperationOnDB {
    private HibernateUtil util = HibernateUtil.getInstance();

    public int getSumOfSalary(long id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Project> query =
                // from table Project as alias p join "field" developers (set) where project's id is ?
                session.createQuery("from Project as p inner join fetch p.developers as d where p.id = : id",
                                Project.class)
                        .setParameter("id", id);
        List<Project> list = query.list();
        int sum = list.stream()
                .flatMap(o -> o.getDevelopers().stream())
                .mapToInt(o -> o.getSalary())
                .sum();
        transaction.commit();
        session.close();

        return sum;
    }

    public List<Developer> getListOfDevelopers(int id) {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Project> query =
                session.createQuery("from Project as p inner join fetch p.developers as d where p.id = : id",
                                Project.class)
                        .setParameter("id", id);
        List<Project> list = query.list();
        List<Developer> result = list.stream()
                .flatMap(o -> o.getDevelopers().stream())
                .collect(Collectors.toList());
        transaction.commit();
        session.close();

        return result;
    }

    public List<Developer> getListOfJavaDev() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Skills> query = session.createQuery("from Skills as s inner join fetch s.developer",
                Skills.class);
        List<Skills> list = query.list();
        List<Developer> result = list.stream()
                .filter(it -> it.getTechnology() == Skills.Technology.Java)
                .map(o -> o.getDeveloper())
                .collect(Collectors.toList());
        transaction.commit();
        session.close();

        return result;
    }

    public List<Developer>  getListMidDev() {
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Skills> query = session.createQuery("from Skills as s inner join fetch s.developer",
                Skills.class);
        List<Skills> list = query.list();
        List<Developer> result = list.stream()
                .filter(it -> it.getLevelOfPosition().equals("Meddle"))
                .map(o -> o.getDeveloper())
                .collect(Collectors.toList());
        transaction.commit();
        session.close();

        return result;

    }
    public List<Object> getListOfProject(){
        Session session = util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

//        select projects.start_date as st_d, projects.name_of_project as name_pr, projects.id_project as id_pr,\n" +
//            " count(*) as total_dev\n" +
//            "\tfrom projects \n" +
//            "\tjoin project_developer on projects.id_project = project_developer.id_project\n" +
//            "\tgroup by id_pr\n";
        Query<String[]> query = session.createQuery("select p,d from Project as p inner join fetch p.developers as d group by p.id",
                String[].class);

//        Query<Long> query = session.createQuery("select count(*)from Project pr",
//                Long.class);
        List<String[]> list1 = query.list();

        System.out.println("list1 = " + list1);
        List<Object> collect = list1.stream().flatMap(Arrays::stream).collect(Collectors.toList());
//        System.out.println("s = " + );

//        System.out.println("queryString = " + queryString);

//        Long singleResult = query.getSingleResult();
//        System.out.println("singleResult = " + singleResult);
//        List<Object> list = query.list();

//        result.stream()
//                .map(it-> it.getDate() + it.getNameOfProject() + it.getDescription())
//                .collect(Collectors.toList());
//        transaction.commit();
//        session.close();
        List<Object> list = new ArrayList<>();
        return list;

    }

    public static void main(String[] args) {
        OperationOnDB operationOnDB = new OperationOnDB();
//        System.out.println("operationOnDB.getSumOfSalary(4) = " + operationOnDB.getSumOfSalary(4));
//        System.out.println("operationOnDB.getListOfDevelopers(4) = " + operationOnDB.getListOfDevelopers(4));
//        System.out.println("operationOnDB.getListOfJavaDev() = " + operationOnDB.getListOfJavaDev());
//        System.out.println("operationOnDB.getListMidDev() = " + operationOnDB.getListMidDev());
        System.out.println("operationOnDB.getListOfProject() = " + operationOnDB.getListOfProject());

    }

}


//    public List<String> getListOfProject() {
//        List<String> result = new ArrayList<>();
//        try (ResultSet resultSet = getListOfProjectSt.executeQuery()) {
//            while (resultSet.next()) {
//                result.add(resultSet.getString("st_d") + " " +
//                        resultSet.getString("name_pr") + " " +
//                        resultSet.getString("total_dev"));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//
//        }
//        return result;
//    }
//
//
//    public static final String GET_SUM_OF_SALARY_BY_ID_PROJECT_QUERY = "select project_developer.id_project as id_pr, sum(developers.salary) as sum_of_salary\n" +
//            "\tfrom developers \n" +
//            "\tjoin project_developer on developers.id = project_developer.id_developer \n" +
//            "\tgroup by id_pr\n" +
//            "\thaving id_pr =?\n";
//
//    public static final String GET_LIST_OF_DEV_BY_ID_PROJECT = "select project_developer.id_project as id_pr, developers.id as id_dev,\n" +
//            "developers.firstName as f_name,  developers.secondName as s_name, \n" +
//            "developers.age as age, developers.sex as sex, developers.salary as salary\n" +
//            "from developers \n" +
//            "join project_developer on developers.id = project_developer.id_developer \n" +
//            "having id_pr = ?\n";
//
//    public static final String GET_LIST_OF_JAVA_DEV = "select developers.id as id_dev, skills.technology as tech,\n" +
//            " developers.firstName as f_name,  developers.secondName as s_name, developers.age as age, \n" +
//            " developers.sex as sex, developers.salary as salary\n" +
//            " from developers\n" +
//            " join skills on developers.id = skills.id_developer\n" +
//            " having tech = \"java\";";
//    public static final String GET_LIST_OF_MID_DEV = "select distinct developers.id as id_dev, skills.levelOfPosition as lev,\n" +
//            "developers.firstName as f_name,  developers.secondName as s_name, \n" +
//            "developers.age as age, developers.sex as sex, developers.salary as salary\n" +
//            "from developers \n" +
//            "join skills on developers.id = skills.id_developer \n" +
//            "having lev = \"middle\"";
//
//
//    public static final String GET_LIST_OF_PROJECT_DATE_NAME_QTY_OF_DEV_FORMAT = "select projects.start_date as st_d, projects.name_of_project as name_pr, projects.id_project as id_pr,\n" +
//            " count(*) as total_dev\n" +
//            "\tfrom projects \n" +
//            "\tjoin project_developer on projects.id_project = project_developer.id_project\n" +
//            "\tgroup by id_pr\n";

