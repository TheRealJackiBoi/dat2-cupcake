package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.BottomCakeFacade;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupCakeFacade;
import dat.backend.model.persistence.TopCakeFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
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

        try {
            int orderId = (int) session.getAttribute("currentOrderId");
            List<CupCake> cupCakeList = CupCakeFacade.getCakesByOrderId(orderId, connectionPool);
            List<CupCake> currentOrder = new ArrayList<>();
            List<Float> totalPriceList = new ArrayList<>();
            for(CupCake o: cupCakeList) {
                int cupcakeId = o.getCupCakeId();
                //CupCake cupCake = CupCakeFacade.getCakeByCakeId(cupcakeId, connectionPool);
                BottomCake bottomCake = BottomCakeFacade.getBottom(o.getBottomId(),connectionPool);
                TopCake topCake = TopCakeFacade.getTop(o.getTopId(), connectionPool);
                int amount = o.getQuantity();
                float price = o.getPrice();
                CupCake tempCupcake = new CupCake(bottomCake, topCake, amount, price, cupcakeId);
                currentOrder.add(tempCupcake);

            }
            for (CupCake c: currentOrder) {
                float price = c.getPrice();
                totalPriceList.add(price);

            }
            float sum = 0;
            for (float number: totalPriceList) {
                sum += number;
            }


            List<Float> currentOrderPriceList = new ArrayList<>();

            session.setAttribute("totalPrice,", sum);
            session.setAttribute("list", currentOrder);
            request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);


        } catch (DatabaseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ServletContext getServletContext() {
        return super.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
