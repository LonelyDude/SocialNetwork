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
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setBirth(resultSet.getDate("birth").toLocalDate());
                user.setSex(resultSet.getString("sex").charAt(0));
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
        }
        return user;
    }

    public User getUser(int id){
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setBirth(resultSet.getDate("birth").toLocalDate());
                user.setSex(resultSet.getString("sex").charAt(0));
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
        }
        return user;
    }

    public boolean checkEmail(String email){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try{
            Connection connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT email FROM authorization WHERE email = ?");
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
            try {
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String addUser(User user, String email, String password) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String result = null;
        try {
            Connection connection = dataSource.getConnection();

            statement = connection.prepareCall("SELECT insertUser(?, ?, ?, ?, ?, ?);");
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, java.sql.Date.valueOf(user.getBirth()));
            statement.setString(4, String.valueOf(user.getSex()));
            statement.setString(5, email);
            statement.setString(6, password);

            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getTimestamp(1).toString();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void confirmEmail(String token){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("DELETE FROM email_confirming WHERE token = ?");
            statement.setString(1, token);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDataSource(ConnectionManager dataSource) {
        this.dataSource = dataSource;
    }
}
