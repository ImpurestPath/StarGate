package ru.ifmo.oop.dataAccess;

import ru.ifmo.oop.dataAccess.DTO.UserDTO;
import ru.ifmo.oop.dataAccess.exception.DatabaseError;

public interface UserDAO extends DAO<UserDTO> {
    UserDTO get(String name) throws DatabaseError;
}
