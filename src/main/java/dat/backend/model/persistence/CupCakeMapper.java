package dat.backend.model.persistence;

import dat.backend.model.entities.BottomCake;
import dat.backend.model.entities.CupCake;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class CupCakeMapper {
    static CupCake getCakeByCakeId(int cupcakeId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        CupCake cupCake = null;

        String sql = "SELECT * FROM cupcake WHERE cupcake_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, cupcakeId);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int quantity = rs.getInt("quantity");
                    int orderId = rs.getInt("order_id");
                    int bottomId = rs.getInt("bottom_id");
                    int topId = rs.getInt("top_id");
                    float price = rs.getFloat("price");
                    cupCake = new CupCake(cupcakeId, quantity, orderId, bottomId, topId, price);
                } else {
                    throw new DatabaseException("no cakes available");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong when the database tried to get cupcakes");
        }
        return cupCake;
    }

    static List<CupCake> getCakeSByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<CupCake> cupCakeList = new ArrayList<>();
        CupCake cupCake = null;

        String sql = "SELECT * FROM cupcake WHERE order_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int cupcakeId = rs.getInt("cupcake_id");
                    int quantity = rs.getInt("quantity");

                    int bottomId = rs.getInt("bottom_id");
                    int topId = rs.getInt("top_id");
                    float price = rs.getFloat("price");
                    cupCake = new CupCake(cupcakeId, quantity, orderId, bottomId, topId, price);
                    cupCakeList.add(cupCake);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong when the database tried to get cupcakes");
        }
        return cupCakeList;
    }

    static List<CupCake> getAllCakes(ConnectionPool connectionPool) throws DatabaseException {
        List<CupCake> cupCakeList = new ArrayList<>();

        String sql = "SELECT * FROM cupcake";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int cupcakeId = rs.getInt("cupcake_id");
                    int quantity = rs.getInt("quantity");
                    int orderId = rs.getInt("order_id");
                    int bottomId = rs.getInt("bottom_id");
                    int topId = rs.getInt("top_id");
                    float price = rs.getFloat("price");
                    cupCakeList.add(new CupCake(cupcakeId, quantity, orderId, bottomId, topId, price));
                } else {
                    throw new DatabaseException("no cakes available");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong when the database tried to get cupcakes");
        }
        return cupCakeList;
    }


    public static float calculatePrice(int cupcakeBottom, int cupcakeTop, int numberOfCakes, ConnectionPool connectionPool) throws DatabaseException {

        float bottomCakePrice = BottomCakeMapper.getBottomPrice(cupcakeBottom, connectionPool);
        float topCakePrice = TopCakeFacade.getTopPrice(cupcakeTop, connectionPool);

        float price = bottomCakePrice + topCakePrice * numberOfCakes;
        return price;
    }

    public static int addCupCake(int numberOfCakes, int newOrderId, int cupcakeBottom, int cupcakeTop, float price, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO cupcake (quantity, order_id, bottom_id, top_id, price) VALUES (?,?,?,?,?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, numberOfCakes);
                ps.setInt(2, newOrderId);
                ps.setInt(3, cupcakeBottom);
                ps.setInt(4, cupcakeTop);
                ps.setFloat(5, price);
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "There was a problem adding the new cupcake to the database");
        }
        return 0;
    }

    public static int removeCupCake(int cupcakeId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM cudia_dk_db_cupcake.cupcake WHERE cupcake_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)){
             ps.setInt(1, cupcakeId);

             ps.executeUpdate();

             return ps.getUpdateCount();
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "there was a database problem when trying to remove cupcake from your current order");
        }
        return 0;
    }
}
