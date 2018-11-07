package ru.ifmo.oop.db;

import ru.ifmo.oop.db.DTO.UserDB;
import ru.ifmo.oop.db.Exception.ExceptionDAO;

public interface UserDAO {
    UserDB get(String name) throws ExceptionDAO;
    int insert(UserDB user) throws ExceptionDAO;
    void delete(int idUser) throws ExceptionDAO;
    void update(int idUser, UserDB user) throws ExceptionDAO;
}
