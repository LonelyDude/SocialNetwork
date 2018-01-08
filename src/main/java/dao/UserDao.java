package dao;

import dbc.ConnectionManager;
import entity.User;

import java.sql.*;

public class UserDao {

    public ConnectionManager dataSource;

    public User login(String email, String password){
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM user INNER JOIN authorization " +
                    "ON user.id = authorization.user_id " +
                    "WHERE email= ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if(resultSet.next()){{
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setBirth(resultSet.getDate("birth").toLocalDate());
                user.setSex(resultSet.getString("sex").charAt(0));
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

    public boolean checkEmail(String email){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try{
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT email FROM email_confirming WHERE email = ?");
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                result = false;
            }else{
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    public boolean addUser(User user){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try{
            connection = dataSource.getConnection();

            statement = connection.prepareStatement("INSERT INTO user VALUE(?, ?, ?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, java.sql.Date.valueOf(user.getBirth()));
            statement.setString(4, String.valueOf(user.getSex()));

            statement = connection.prepareStatement("INSERT INTO authorization VALUE(?, ?, ?)");

            connection.commit();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }finally {
            return result;
        }

    }

    public boolean confirmEmail(int token){

        return true;
    }

    public void setDataSource(ConnectionManager dataSource) {
        this.dataSource = dataSource;
    }
}
