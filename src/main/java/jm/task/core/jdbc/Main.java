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
        System.out.println("User с именем – John добавлен в базу данных");

        userService.saveUser("Ann", "Adams", (byte) 35);
        System.out.println("User с именем – Ann добавлен в базу данных");

        userService.saveUser("Ben", "Wallis", (byte) 27);
        System.out.println("User с именем – Ben добавлен в базу данных");

        userService.saveUser("Alen", "Dalles", (byte) 33);
        System.out.println("User с именем – Alen добавлен в базу данных");

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
