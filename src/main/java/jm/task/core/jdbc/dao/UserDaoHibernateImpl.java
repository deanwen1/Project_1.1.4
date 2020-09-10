package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.Session;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    Util util = new Util();
    User user = new User();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS jm.users " +
                "(id INT NOT NULL AUTO_INCREMENT, " +
                " name VARCHAR(45) NOT NULL, " +
                " lastname VARCHAR(45) NOT NULL, " +
                " age INT NOT NULL, " +
                " PRIMARY KEY (id))";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void dropUsersTable() {
        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS jm.users";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        session.getTransaction().commit();
        System.out.println("User с именем - " + name + " добавлен в базу данныых");
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {

        List<User> list;
        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        list = (List<User>) session.createQuery("From " + User.class.getSimpleName()).list();
        session.getTransaction().commit();
        for (User user : list) {
            System.out.println(user);
        }
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {

        Session session = util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}