package dat.backend.model.persistence;

import dat.backend.model.entities.BottomCake;
import dat.backend.model.entities.CupCake;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class CupCakeFacade
{
    public static CupCake getCakeByCakeId(int cakeId, ConnectionPool connectionPool) throws DatabaseException
    {
        return CupCakeMapper.getCakeByCakeId(cakeId, connectionPool);
    }

    public static List<CupCake> getCakesByOrderId(int orderId, ConnectionPool connectionPool) throws DatabaseException
    {
        return CupCakeMapper.getCakeSByOrderId(orderId, connectionPool);
    }

    public static List<CupCake> getAllCakes(ConnectionPool connectionPool) throws DatabaseException
    {
        return CupCakeMapper.getAllCakes(connectionPool);
    }

    public static float calculatePrice(int cupcakeBottom, int cupcakeTop, int numberOfCakes, ConnectionPool connectionPool) throws DatabaseException{
        return CupCakeMapper.calculatePrice(cupcakeBottom, cupcakeTop, numberOfCakes, connectionPool);
    }

    public static int addCupCake(int numberOfCakes, int newOrderId, int cupcakeBottom, int cupcakeTop, float price, ConnectionPool connectionPool) throws DatabaseException {
        return CupCakeMapper.addCupCake(numberOfCakes, newOrderId, cupcakeBottom, cupcakeTop, price, connectionPool);
    }

    public static int removeCupCake(int cupcakeId, ConnectionPool connectionPool) throws DatabaseException {
        return CupCakeMapper.removeCupCake(cupcakeId, connectionPool);
    }
}
