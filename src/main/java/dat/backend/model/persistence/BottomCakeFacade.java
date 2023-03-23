package dat.backend.model.persistence;

import dat.backend.model.entities.BottomCake;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class BottomCakeFacade
{
    public static BottomCake getBottom(int bottomId, ConnectionPool connectionPool) throws DatabaseException
    {
        return BottomCakeMapper.getBottom(bottomId, connectionPool);
    }

    public static List<BottomCake> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException
    {
        return BottomCakeMapper.getAllBottoms(connectionPool);
    }
}
