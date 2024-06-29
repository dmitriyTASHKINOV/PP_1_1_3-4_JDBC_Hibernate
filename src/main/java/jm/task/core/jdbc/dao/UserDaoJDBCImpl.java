package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        //PreparedStatement preparedStatement = null;
        String sql ="CREATE TABLE User(id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(50),lastName VARCHAR(50),age TINYINT )";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица User успешно создана");
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }

    public void dropUsersTable() {
     String sql = "DROP TABLE User";
     try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
         preparedStatement.executeUpdate();
         System.out.println("Таблица удалена");
     } catch (SQLException e) {
        e.printStackTrace();
     }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name,lastName,age);
     String sql = "INSERT INTO User(name,lastName,age)VALUES (?, ?, ?)";
     try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1,user.getName());
         preparedStatement.setString(2, user.getLastName());
         preparedStatement.setByte(3, user.getAge());
         preparedStatement.executeUpdate();
         System.out.println("пользователь добавлен");
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
    }

    public void removeUserById(long id) {
        if (id <= 0) {
            System.out.println("Некорректный идентификатор пользователя");
            return;
        }

        String sql = "DELETE FROM User WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Пользователь с id " + id + " удален");
            } else {
                System.out.println("Пользователь с id " + id + " не найден");
            }

            System.out.println("Пользователь удален");

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении пользователя", e);
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM User";
        List<User> userList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

        public void cleanUsersTable() {
       String sql = "DELETE FROM User";
       try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.executeUpdate();
           System.out.println("таблица очищена");
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        }
}
