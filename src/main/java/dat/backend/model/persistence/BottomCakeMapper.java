package dat.backend.model.persistence;

import dat.backend.model.entities.BottomCake;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class BottomCakeMapper
{
    static BottomCake getBottom(int bottomId, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        BottomCake bottomCake = null;

        String sql = "SELECT * FROM bottom WHERE bottom_id = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, bottomId);

                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    bottomCake = new BottomCake(bottomId, name, price);
                } else
                {
                    throw new DatabaseException("Something went wrong with getting the cupcakebottoms");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return bottomCake;
    }

    static List<BottomCake> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException{
        List<BottomCake> bottomCakeList = new ArrayList<>();

        String sql = "SELECT * FROM bottom";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int bottomId = rs.getInt("bottom_id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    bottomCakeList.add(new BottomCake(bottomId, name, price));
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return bottomCakeList;
    }

    static float getBottomPrice(int bottomId, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        float price;

        String sql = "SELECT price FROM bottom WHERE bottom_id = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, bottomId);

                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    price = rs.getFloat("price");
                } else
                {
                    throw new DatabaseException("Something went wrong with getting the cupcakebottom price");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return price;
    }

}
