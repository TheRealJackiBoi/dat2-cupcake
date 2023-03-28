package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class OrderFacade
{
    public static List<Order> getOrdersByUserEmail(String userEmail, ConnectionPool connectionPool) throws DatabaseException
    {
        return OrderMapper.getOrdersByUserEmail(userEmail, connectionPool);
    }

    public static Order getOrderByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException
    {
        return OrderMapper.getOrderByOrderId(orderId, connectionPool);
    }

    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException
    {
        return OrderMapper.getAllOrders(connectionPool);
    }

    public static int addOrder(String userEmail, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.addOrder(userEmail, connectionPool);
    }

    public static void togglePayment(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        OrderMapper.togglePayed(orderId, connectionPool);
    }

    public static float calculateTotalPrice(int orderId, ConnectionPool connectionPool) throws DatabaseException{
        return OrderMapper.calculateTotalPrice(orderId, connectionPool);
    }
}
