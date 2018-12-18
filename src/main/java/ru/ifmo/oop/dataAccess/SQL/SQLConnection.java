package ru.ifmo.oop.dataAccess.SQL;

import org.sqlite.JDBC;
import ru.ifmo.oop.dataAccess.*;
import ru.ifmo.oop.dataAccess.exception.ConnectionError;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection implements ConnectionDAO<Connection> {
    private static final String CON_STR = "jdbc:sqlite:";
    private static SQLConnection instance = null;
    private Connection connection;

    public SQLConnection(String url) throws ConnectionError {
        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(CON_STR + url);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new ConnectionError(e);
        }
    }

    /*public static synchronized SQLConnection getInstance(String filename) throws ConnectionError {
        if (instance == null)
            instance = new SQLConnection(filename);
        return instance;
    }*/

    public void commit() throws DatabaseError {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }


}
