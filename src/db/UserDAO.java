package db;

public interface UserDAO {
    UserDB get(String name) throws ExceptionDAO;
    int insert(UserDB user) throws ExceptionDAO;
    void delete(int idUser) throws ExceptionDAO;
    void update(int idUser, UserDB user) throws ExceptionDAO;
}
