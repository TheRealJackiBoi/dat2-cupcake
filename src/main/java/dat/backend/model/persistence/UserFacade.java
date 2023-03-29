package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import javax.xml.crypto.Data;
import java.util.List;

public class UserFacade
{
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.login(email, password, connectionPool);
    }

    public static User createUser(String email, String password, int admin, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.createUser(email, password, admin, connectionPool);
    }

    public static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.getAllUsers(connectionPool);
    }

    public static User getUserByEmail(String email, ConnectionPool connectionPool) {
        return UserMapper.getUserByEmail(email, connectionPool);
    }

    public static User updateUser(float balance, String email, int admin, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.updateUser(email, balance, admin, connectionPool);
    }
}
