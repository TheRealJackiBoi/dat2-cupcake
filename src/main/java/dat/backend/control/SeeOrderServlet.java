package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.BottomCake;
import dat.backend.model.entities.CupCake;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.TopCake;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupCakeFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SeeOrderServlet", value = "/SeeOrder")
public class SeeOrderServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        super.init();
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<CupCake> cupcakes;
        Order order;

        int orderId = Integer.parseInt(request.getParameter("orderid"));

        try {
            List<CupCake> orderList = CupCakeFacade.getCakesByOrderId(orderId, connectionPool);
            cupcakes = new ArrayList<>();
            for(CupCake o : orderList) {
                BottomCake bottomCake = o.getBottomCake();
                TopCake topCake = o.getTopCake();
                int amount = o.getAmount();
                float price = o.getPrice();
                CupCake tempCupcake = new CupCake(bottomCake, topCake, amount, price);
                cupcakes.add(tempCupcake);
            }
            request.setAttribute("orderCupcakes", cupcakes);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}
