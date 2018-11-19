package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.UserDTO;
import ru.ifmo.oop.dataAccess.exception.ExceptionDAO;

public interface UserDAO {
    UserDTO get(String name) throws ExceptionDAO;
    int insert(UserDTO user) throws ExceptionDAO;
    void delete(int idUser) throws ExceptionDAO;
    void update(int idUser, UserDTO user) throws ExceptionDAO;
}
