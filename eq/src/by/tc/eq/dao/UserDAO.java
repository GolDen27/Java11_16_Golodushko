package by.tc.eq.dao;

import by.tc.eq.bean.User;
import by.tc.eq.dao.exception.DAOException;

public interface UserDAO {
    void addUser (User user) throws DAOException;
    void deleteUser (User user) throws DAOException;
    void changeUser (User oldUser, User newUser) throws DAOException;
    User searchUser (String passportID) throws DAOException;
}
