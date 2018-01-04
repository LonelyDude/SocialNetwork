package DBC;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class ConnectionManager {

    private DataSource dataSource;

    public Connection getConnection() throws SQLException {
        if(dataSource == null){
            try {
                InitialContext context = new InitialContext();
                dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/MyLocalDB");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource.getConnection();
    }

    public <T> T doInTransaction(Callable<T> call) {
        Connection connection = null;
        T result = null;
        try {
            connection = getConnection();
            result = call.call();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            return result;
        }
    }
}
