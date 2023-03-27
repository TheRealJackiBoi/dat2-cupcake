package dat.backend.model.persistence;

import dat.backend.model.entities.TopCake;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TopCakeMapper
{

    static TopCake getTop(int topId, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        TopCake topCake = null;

        String sql = "SELECT * FROM top WHERE top_id = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, topId);

                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    topCake = new TopCake(topId, name, price);
                } else
                {
                    throw new DatabaseException("Something went wrong with getting toppings");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return topCake;
    }

    static List<TopCake> getAllTops(ConnectionPool connectionPool) throws DatabaseException{
        List<TopCake> topCakeList = new ArrayList<>();

        String sql = "SELECT * FROM top";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    int topId = rs.getInt("bottom_id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    topCakeList.add(new TopCake(topId, name, price));
                } else
                {
                    throw new DatabaseException("No toppings available");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return topCakeList;
    }

    static Float getTopPrice(int topId, ConnectionPool connectionPool) throws DatabaseException
    {
        Logger.getLogger("web").log(Level.INFO, "");

        float price;

        String sql = "SELECT price FROM top WHERE top_id = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, topId);

                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    price = rs.getFloat("price");
                } else
                {
                    throw new DatabaseException("Something went wrong with getting toppings price");
                }
            }
        } catch (SQLException ex)
        {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return price;
    }


}
