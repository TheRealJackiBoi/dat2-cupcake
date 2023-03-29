package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper
{
    static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    int admin = rs.getInt("admin");
                    float balance = rs.getFloat("balance");
                    user = new User(email, password, balance, admin);
                } else
                {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    public static User createUser(String email, String password, int admin, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");
        User user;
        String sql = "insert into user (email, password) values (?,?)";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1)
                {
                    user = new User(email, password, 0, admin);
                } else
                {
                    throw new DatabaseException("The user with username = " + email + " could not be inserted into the database");
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DatabaseException(ex, "Could not insert username into database");
        }
        return user;
    }

    static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT * FROM user";

        List<User> userList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    float balance = resultSet.getFloat("balance");
                    int admin = resultSet.getInt("admin");

                    userList.add(new User(email, password, balance, admin));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "there was an error retrieving information from the database");
        }

        return userList;
    }


    public static User getUserByEmail(String email, ConnectionPool connectionPool) {
        Logger.getLogger("web").log(Level.INFO, "");
        String sql = "SELECT * FROM user WHERE email = ?";

        List<User> userList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String email_ = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    float balance = resultSet.getFloat("balance");
                    int admin = resultSet.getInt("admin");

                    User newUser = new User(email_, password, balance, admin);
                    return newUser;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User updateUser(String email, float balance, int admin, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        //String sql = "UPDATE user SET (email, password, balance, admin) = (?, ?, ?, ?) WHERE email = ?";
        String sql = "UPDATE user SET balance = ?, admin = ? WHERE email = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setFloat(1, balance);
                ps.setInt(2, admin);
                ps.setString(3, email);
                ps.executeUpdate();
                }
            } catch(SQLException e) {
            throw new DatabaseException(e, "Failed to update user information");

        }
        return null;
    }
}
