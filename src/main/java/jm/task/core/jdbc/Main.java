package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Dmitry","Tashkinov",(byte) 26);
        userService.saveUser("Sofi","Tashkinova",(byte) 24);
        userService.saveUser("Elena","Tashkinova",(byte) 47);
        userService.saveUser("Alexsandr","Tashkinov",(byte) 16);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
