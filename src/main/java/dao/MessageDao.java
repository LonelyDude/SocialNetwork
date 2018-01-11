package dao;

import dbc.ConnectionManager;
import entity.Message;
import entity.User;
import sun.misc.resources.Messages_es;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MessageDao {

    private ConnectionManager dataSource;

    public List<Message> getMessages(User user){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Message> list = new LinkedList<>();
        try{
            connection = dataSource.getConnection();

            statement = connection.prepareStatement("SELECT * FROM message WHERE (message.user_id_from = ? OR message.user_id_to = ?)");
            statement.setInt(1, user.getId());
            statement.setInt(2, user.getId());
            resultSet = statement.executeQuery();

            if(resultSet == null){
                return null;
            }

            while (resultSet.next()){
                Message message = new Message();
                message.setFrom(resultSet.getInt("user_id_from"));
                message.setTo(resultSet.getInt("user_id_to"));
                message.setContent(resultSet.getString("content"));
                message.setDate(resultSet.getDate("date").toLocalDate());
                message.setId(resultSet.getInt("id"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void sendMessage(Message message){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = dataSource.getConnection();

            statement = connection.prepareStatement("INSERT INTO message VALUES(?, ?, ?, ?, NULL)");
            statement.setInt(1, message.getFrom());
            statement.setInt(2, message.getTo());
            statement.setString(3, message.getContent());
            statement.setDate(4, java.sql.Date.valueOf(message.getDate()));

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ConnectionManager getDataSource() {
        return dataSource;
    }

    public void setDataSource(ConnectionManager dataSource) {
        this.dataSource = dataSource;
    }
}
