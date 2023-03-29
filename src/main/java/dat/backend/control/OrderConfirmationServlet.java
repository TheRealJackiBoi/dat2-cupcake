package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

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
    }

    @Override
    public ServletContext getServletContext() {
        return super.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        try {
            int orderId = (int) session.getAttribute("currentOrderId");
            Order order = OrderFacade.getOrderByOrderId(orderId, connectionPool);
            float orderPrice = OrderFacade.calculateTotalPrice(orderId, connectionPool);

            int paymentOption = Integer.parseInt(request.getParameter("paymentmethod"));
            if (paymentOption == 0) {
            // Checking if user has enough money for the order
            if (orderPrice <= user.getBalance()) {

                UserFacade.updateUser(user.getBalance() - orderPrice, user.getUsername(), user.getAdmin(), connectionPool);
                OrderFacade.togglePayment(orderId, connectionPool);

                order = OrderFacade.getOrderByOrderId(orderId, connectionPool);
                session.setAttribute("user", UserFacade.login(user.getUsername(), user.getPassword(), connectionPool));
            } else {
                request.setAttribute("balanceInsufficient", true);
                request.getRequestDispatcher("ordering").forward(request,response);
            }}
                List<CupCake> cupCakeList = CupCakeFacade.getCakesByOrderId(orderId, connectionPool);
                List<CupCake> currentOrder = new ArrayList<>();
                for (CupCake o : cupCakeList) {
                    int cupcakeId = o.getCupCakeId();
                    //CupCake cupCake = CupCakeFacade.getCakeByCakeId(cupcakeId, connectionPool);
                    BottomCake bottomCake = BottomCakeFacade.getBottom(o.getBottomId(), connectionPool);
                    TopCake topCake = TopCakeFacade.getTop(o.getTopId(), connectionPool);
                    int amount = o.getQuantity();
                    float price = o.getPrice();
                    CupCake tempCupcake = new CupCake(bottomCake, topCake, amount, price, cupcakeId);
                    currentOrder.add(tempCupcake);

                }

                List<Float> currentOrderPriceList = new ArrayList<>();

                //makes new order next time you want to order
                session.setAttribute("currentOrderId", null);
                session.setAttribute("currentOrderList", null);
                session.setAttribute("totalprice", 0);

                session.setAttribute("orderConfirmOrder", order);
                session.setAttribute("orderTotalPrice", OrderFacade.calculateTotalPrice(orderId, connectionPool));
                session.setAttribute("list", currentOrder);
                request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);



        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
