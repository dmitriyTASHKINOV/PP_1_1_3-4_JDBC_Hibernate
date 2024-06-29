package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/mydbtest";
    private static final String DB_USERNAME = "roottyt";
    private static final String DB_PASSWORD = "roottyt";
     public static Connection getConnection(){
         Connection connection = null;
         try {
             Class.forName(DB_DRIVER);
             connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
             System.out.println("vse ok");
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
             System.out.println("vse not ok");
         }
         return connection;
     }
}
