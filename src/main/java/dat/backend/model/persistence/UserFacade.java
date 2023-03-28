package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class UserFacade
{
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.login(email, password, connectionPool);
    }

    public static User createUser(String email, String password, int admin, ConnectionPool connectionPool) throws DatabaseException {
        return UserMapper.createUser(email, password, admin, connectionPool);
    }

    public static List<User> getAllUsers(ConnectionPool connectionPool) {
        return UserMapper.getAllUsers(connectionPool);
    }
}
