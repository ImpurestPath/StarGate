package ru.ifmo.oop.db.SQL;

import org.sqlite.JDBC;
import ru.ifmo.oop.db.ConnectionDAO;
import ru.ifmo.oop.db.Exception.ExceptionDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection implements ConnectionDAO {
    private static final String CON_STR = "jdbc:sqlite:";
    private Connection connection;
    private static SQLConnection instance = null;
    public static synchronized SQLConnection getInstance(String filename) throws ExceptionDAO {
        if (instance == null)
            instance = new SQLConnection(filename);
        return instance;
    }
    private SQLConnection(String url) throws ExceptionDAO {
        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(CON_STR + url);
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
