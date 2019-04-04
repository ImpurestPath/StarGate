package ru.ifmo.oop.domain.interfaces;

import ru.ifmo.oop.dataAccess.exception.DatabaseError;
import ru.ifmo.oop.domain.User;

public interface IUserManager extends Manager<User>{
    User get(String name) throws DatabaseError;
}
