package dao;

import dbc.TransactionManager;
import entity.User;

import java.sql.*;

public class UserDao {

    public TransactionManager dataSource;

    public User login(String email, String password){
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM user WHERE email= ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if(resultSet.next()){{
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setBirth(resultSet.getDate("birth"));
                user.setSex(resultSet.getString("sex").charAt(0));
                user.setStatus(resultSet.getBoolean("status"));
                user.setEmail(email);
                user.setPassword(password);
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        }
    }

    public boolean addUser(User user){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try{
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT email FROM user WHERE email = ?");
            statement.setString(1, user.getEmail());
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = false;
                return result;
            }
            statement = connection.prepareStatement("INSERT INTO user VALUE(?, ?, ?, ?, ?, ?, false)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    public void setDataSource(TransactionManager dataSource) {
        this.dataSource = dataSource;
    }
}
