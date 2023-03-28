package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.CupCake;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
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
        int cupcakeBottom = Integer.parseInt(request.getParameter("bottomcake"));
        int cupcakeTop = Integer.parseInt(request.getParameter("topcake"));
        int numberOfCakes = Integer.parseInt(request.getParameter("numberofcakes"));

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CupCake cupCake = null;
        CupCakeFacade cupCakeFacade = new CupCakeFacade();
        List<CupCake> cupCakeList = null;
        Order order = null;
        request.setAttribute("currentOrderId", order.getOrderId());
        request.setAttribute("timeStamp", order.getOrderDate());

        try {
            cupCakeList = cupCakeFacade.getCakesByOrderId(cupCake.getOrderId(), connectionPool);
            float price = CupCakeFacade.calculatePrice(cupcakeBottom, cupcakeTop, numberOfCakes, connectionPool);
            CupCake tempCupCake = new CupCake(BottomCakeFacade.getBottom(cupcakeBottom, connectionPool),
                    TopCakeFacade.getTop(cupcakeTop, connectionPool), numberOfCakes, price);
            List<CupCake> currentOrder = new ArrayList<>();
            List<Float> currentOrderPriceList = new ArrayList<>();
            currentOrder.add(tempCupCake);

            for (int i = 0; i <= currentOrder.size(); i++){
               float cp = currentOrder.get(i).getPrice();
                currentOrderPriceList.add(cp);
            }
            int sum = 0;

            for (float number : currentOrderPriceList){
                sum += number;
            }

            session.setAttribute("list", currentOrder);
            session.setAttribute("totalPrice", sum);


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
