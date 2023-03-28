package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrdersServlet", value = "/orders")
public class OrdersServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        super.init();

        connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //sikre at brugeren er logget ind
        HttpSession session = request.getSession();


        User user = (User) session.getAttribute("user"); // adding user to session scope
        //testing if the user is loggin in
        if (user == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        //Creating lists to forward to jsp site
        List<Order> orders;
        List<Float> ordersTotalPrice = new ArrayList<>();
        if (user.getAdmin() == 1) {

        }
            try {
                orders = OrderFacade.getOrdersByUserEmail(user.getUsername(), connectionPool);

                for (Order o : orders) {
                    ordersTotalPrice.add(OrderFacade.calculateTotalPrice(o.getOrderId(), connectionPool));
                }

                session.setAttribute("userOrders", orders);
                session.setAttribute("userOrdersPrice", ordersTotalPrice);
                request.getRequestDispatcher("orders.jsp").forward(request, response);
            }
            catch (DatabaseException e) {
                request.setAttribute("errormessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
