package ru.ifmo.oop.db;

import ru.ifmo.oop.db.DTO.UserDTO;
import ru.ifmo.oop.db.Exception.ExceptionDAO;

public interface UserDAO {
    UserDTO get(String name) throws ExceptionDAO;
    int insert(UserDTO user) throws ExceptionDAO;
    void delete(int idUser) throws ExceptionDAO;
    void update(int idUser, UserDTO user) throws ExceptionDAO;
}
