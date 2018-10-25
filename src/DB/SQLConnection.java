package DB;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    Connection connection;
    private static SQLConnection instance = null;
    public static synchronized SQLConnection getInstance(String file) throws ExceptionDAO {
        if (instance == null)
            instance = new SQLConnection(file);
        return instance;
    }
    SQLConnection(String file) throws ExceptionDAO {
        try {
            // Регистрируем драйвер, с которым будем работать
            // в нашем случае Sqlite
            DriverManager.registerDriver(new JDBC());
            // Выполняем подключение к базе данных
            this.connection = DriverManager.getConnection(CON_STR + file);
        } catch (SQLException e) {
            throw new ExceptionDAO(e);
        }
    }
}
