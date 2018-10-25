package DB;

import java.sql.Connection;

public class SQLLanguageManager implements LanguageDAO {
    private Connection connection;

    public SQLLanguageManager(SQLConnection sqlConnection) throws ExceptionDAO {
        this.connection = sqlConnection.connection;
    }

}
