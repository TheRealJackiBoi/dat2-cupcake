package dat.backend.model.persistence;

import dat.backend.model.entities.TopCake;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class TopCakeFacade
{
    public static TopCake getTop(int bottomId, ConnectionPool connectionPool) throws DatabaseException
    {
        return TopCakeMapper.getTop(bottomId, connectionPool);
    }

    public static List<TopCake> getAllTops(ConnectionPool connectionPool) throws DatabaseException
    {
        return TopCakeMapper.getAllTops(connectionPool);
    }

    public static float getTopPrice(int cupcakeTop, ConnectionPool connectionPool) throws DatabaseException {
        return TopCakeMapper.getTopPrice(cupcakeTop, connectionPool);
    }
}
