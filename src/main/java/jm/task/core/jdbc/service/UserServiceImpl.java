package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {
   private User user = new User();
   private Util util = new Util();

    public UserServiceImpl() {
    }

    public void createUsersTable() {

        PreparedStatement preparedStatement;
        try {
            preparedStatement = util.getConnection().prepareStatement("CREATE TABLE `jm`.`users`(\n" +
                    "" + " `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "" + " `name` VARCHAR(45) NOT NULL,\n" +
                    "" + " `lastname` VARCHAR(45) NOT NULL,\n" +
                    "" + " `age` INT NOT NULL,\n" + " PRIMARY KEY (`id`))\n" +
                    "" + "ENGINE = InnoDB\n" + "DEFAULT CHARACTER SET = utf8;");

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            dropUsersTable();
            createUsersTable();
        }
    }

    public void dropUsersTable() {

        PreparedStatement preparedStatement;
        try {
            preparedStatement = util.getConnection().prepareStatement("drop table users");
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            createUsersTable();
            dropUsersTable();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        PreparedStatement preparedStatement;
        try {
            preparedStatement = util.getConnection().prepareStatement("insert into users set name = ?, lastName = ?, age = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данныых");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        PreparedStatement preparedStatement;
        try {
            preparedStatement = util.getConnection().prepareStatement("delete from users where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List <User> list = new LinkedList<>();


        PreparedStatement preparedStatement;
        try {
            preparedStatement = util.getConnection().prepareStatement("select * from users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);

                System.out.println(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public void cleanUsersTable() {

        PreparedStatement preparedStatement;
        try {
            preparedStatement = util.getConnection().prepareStatement("Truncate table users");
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
