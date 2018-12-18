package ru.ifmo.oop.domain;

import ru.ifmo.oop.dataAccess.exception.DatabaseError;

public interface IUserManager extends Manager<User>{
    User get(String name) throws DatabaseError;
}
