package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Bob", "Smith", (byte) 20);
        userService.saveUser("Tom", "Jones", (byte) 25);
        userService.saveUser("Jack", "Brown", (byte) 23);
        userService.saveUser("Kurt", "Wilson", (byte) 27);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
