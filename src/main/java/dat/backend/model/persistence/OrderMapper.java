package dat.backend.model.persistence;

import dat.backend.model.entities.CupCake;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class OrderMapper {
    static List<Order> getOrdersByUserEmail(String userEmail, ConnectionPool connectionPool) throws DatabaseException {
        //returns all the orders that the user has made
        Logger.getLogger("web").log(Level.INFO, "");

        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM cudia_dk_db_cupcake.order WHERE user_email = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, userEmail);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    Timestamp orderDate = rs.getTimestamp("orderdate");
                    boolean payed = rs.getBoolean("payed");
                    Order order = new Order(orderId, orderDate, userEmail, payed);
                    orderList.add(order);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong when the database tried to get cupcakes");
        }
        return orderList;
    }

    static Order getOrderByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        //returns a specific order
        Logger.getLogger("web").log(Level.INFO, "");

        Order order = null;

        String sql = "SELECT * FROM cudia_dk_db_cupcake.order WHERE order_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Timestamp orderDate = rs.getTimestamp("orderdate");
                    String userEmail = rs.getString("user_email");
                    boolean payed = rs.getBoolean("payed");
                    order = new Order(orderId, orderDate, userEmail, payed);
                } else {
                    throw new DatabaseException("no cakes available");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong when the database tried to get cupcakes");
        }
        return order;
    }

    static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        //returns all orders, fx for the admins
        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM cudia_dk_db_cupcake.order";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    Timestamp orderDate = rs.getTimestamp("orderdate");
                    String userEmail = rs.getString("user_email");
                    boolean payed = rs.getBoolean("payed");
                    orderList.add(new Order(orderId, orderDate,userEmail, payed));
                } else {
                    throw new DatabaseException("no cakes available");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong when the database tried to get cupcakes");
        }
        return orderList;
    }

    static int addOrder(String userEmail, ConnectionPool connectionPool) throws DatabaseException {
        //set user email ind, auto genererer order id, timestamp og sætter payed til false som udgangspunkt
        String sql = "INSERT INTO cudia_dk_db_cupcake.order (user_email) VALUES (?)";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, userEmail);

                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            } catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e,"Something went wrong with the database, when trying to add a new cupcake");
        }
        return 0;
    }

    static void togglePayed(int orderId, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "UPDATE order SET payed = (1 - payed) WHERE order_id = ?";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e,"Something went wrong when trying to change the boolean of the payment");
        }
    }

    static float calculateTotalPrice(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        List<CupCake> cupCakes;

        cupCakes = CupCakeFacade.getCakesByOrderId(orderId, connectionPool);


        float totalPrice = 0;

        for (CupCake c :
                cupCakes) {
            totalPrice += c.getPrice();
        }

        return totalPrice;
    }
}
