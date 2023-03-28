package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.CupCake;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupCakeFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderConfirmationServlet", urlPatterns = {"/orderConfirmation"})
public class OrderConfirmationServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException{
        this.connectionPool = ApplicationStart.getConnectionPool();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CupCake cupCake = null;

        CupCakeFacade cupCakeFacade = new CupCakeFacade();
        List<CupCake> cupCakeList = null;
        try {
           cupCakeList = cupCakeFacade.getCakesByOrderId(cupCake.getOrderId(), connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        session.setAttribute("list", cupCakeList);
    }

    @Override
    public ServletContext getServletContext() {
        return super.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
