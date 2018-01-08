package dbc;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

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
}
