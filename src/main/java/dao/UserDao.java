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



    public void setDataSource(TransactionManager dataSource) {
        this.dataSource = dataSource;
    }
}
