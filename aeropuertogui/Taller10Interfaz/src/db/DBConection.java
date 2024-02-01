package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 

public class DBConection implements AutoCloseable{
    private Connection connection;

    public DBConection() throws SQLException {
        connect();
    }

    private void connect() throws SQLException {
       
        String url="jdbc:mysql://localhost:3306/aeropuertodb?serverTimezone=UTC";
        String user="root";
        String password="S3n42023";
        connection = DriverManager.getConnection(url, user, password);
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
