package db;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection implements ConnectionDAO{
    private static final String CON_STR = "jdbc:sqlite:";
    private Connection connection;
    private static SQLConnection instance = null;
    public static synchronized SQLConnection getInstance(String file) throws ExceptionDAO {
        if (instance == null)
            instance = new SQLConnection(file);
        return instance;
    }
    private SQLConnection(String file) throws ExceptionDAO {
        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(CON_STR + file);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
    public void commit() throws ExceptionDAO {
        try {
            connection.commit();
        }
        catch (SQLException e){
            throw new ExceptionDAO(e);
        }
    }
}
