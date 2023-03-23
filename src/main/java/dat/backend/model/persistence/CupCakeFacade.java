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
}
