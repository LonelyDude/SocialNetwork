package dbc;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class ConnectionManager {

    private ThreadLocal<Connection> connections = new ThreadLocal<>();

    public Connection getConnection() throws SQLException {
        if(connections.get() == null){
            try {
                InitialContext context = new InitialContext();
                DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/MyDB");
                connections.set(dataSource.getConnection());
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return connections.get();
    }

    public <T> T doInTransaction(Callable<T> call){
        Connection connection = null;
        T result = null;
        try{
            connection = connections.get();
            connection.setAutoCommit(false);
            result = call.call();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
