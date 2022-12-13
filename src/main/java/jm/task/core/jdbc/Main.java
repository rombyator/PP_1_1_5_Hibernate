package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // Create table
        userService.createUsersTable();

        // Add users
        userService.saveUser("John", "Malkovich", (byte) 25);
        userService.saveUser("Ann", "Adams", (byte) 35);
        userService.saveUser("Ben", "Wallis", (byte) 27);
        userService.saveUser("Alen", "Dalles", (byte) 33);

        System.out.println();
        System.out.println("---");
        System.out.println();

        // Get all users
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        // Clear users table
        userService.cleanUsersTable();

        // Delete table
        userService.dropUsersTable();
    }
}
