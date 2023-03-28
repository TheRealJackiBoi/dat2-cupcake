package dat.backend.control;

import com.google.protobuf.Internal;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Ordering", urlPatterns = {"/ordering"})
public class ordering extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //sikre at brugeren er logget ind
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user"); // adding user to session scope


        //testing if the user is loggin in
        if (user == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            try {
                List<BottomCake> bottomCakeList = BottomCakeFacade.getAllBottoms(connectionPool);
                List<TopCake> topCakeList = TopCakeFacade.getAllTops(connectionPool);

                request.setAttribute("bottomCakeList", bottomCakeList);
                request.setAttribute("topCakeList", topCakeList);
                //check if there is already an order going and save that orderId
                if (session.getAttribute("currentOrderId") != null) {
                    int currentOrderId = (int) session.getAttribute("currentOrderId");
                    //find alle cupcakes fra orderId
                    List<CupCake> orderList = CupCakeFacade.getCakesByOrderId(currentOrderId, connectionPool);
                    List<CupCake> currentOrderList = new ArrayList<>();
                    for(CupCake o: orderList) {
                        int cupcakeId = o.getCupCakeId();
                        //CupCake cupCake = CupCakeFacade.getCakeByCakeId(cupcakeId, connectionPool);
                        BottomCake bottomCake = BottomCakeFacade.getBottom(o.getBottomId(),connectionPool);
                        TopCake topCake = TopCakeFacade.getTop(o.getTopId(), connectionPool);
                        int amount = o.getQuantity();
                        float price = o.getPrice();
                        CupCake tempCupcake = new CupCake(bottomCake, topCake, amount, price, cupcakeId);
                        currentOrderList.add(tempCupcake);
                    }
                    session.setAttribute("currentOrderList", currentOrderList);
                }
                //send tilbage til sin egen side når mna klikker
                request.getRequestDispatcher("ordering.jsp").forward(request, response);
            } catch (DatabaseException e) {
                request.setAttribute("errormessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int cupcakeBottom = Integer.parseInt(request.getParameter("bottomcake"));
        int cupcakeTop = Integer.parseInt(request.getParameter("topcake"));
        int numberOfCakes = Integer.parseInt(request.getParameter("numberofcakes"));
        int orderId;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String email = user.getUsername();

        try {
            List<BottomCake> bottomCakeList = BottomCakeFacade.getAllBottoms(connectionPool);
            List<TopCake> topCakeList = TopCakeFacade.getAllTops(connectionPool);

            request.setAttribute("bottomCakeList", bottomCakeList);
            request.setAttribute("topCakeList", topCakeList);
            if (session.getAttribute("currentOrderId") == null) {
                orderId = OrderFacade.addOrder(email, connectionPool);
                //remember to set currentorderID to null in the next servlet when you "confirm order"
                session.setAttribute("currentOrderId", orderId);
            }
            orderId = (int) session.getAttribute("currentOrderId");
            float price = CupCakeFacade.calculatePrice(cupcakeBottom, cupcakeTop, numberOfCakes, connectionPool);
            int cupcakeId = CupCakeFacade.addCupCake(numberOfCakes, orderId, cupcakeBottom, cupcakeTop, price, connectionPool);

            List<CupCake> orderList = CupCakeFacade.getCakesByOrderId(orderId, connectionPool);

            //prøver at løse at jeg kun kan få vist id for top og bottom af hver kage
            CupCake tempCupCake = new CupCake(BottomCakeFacade.getBottom(cupcakeBottom,connectionPool),
                    TopCakeFacade.getTop(cupcakeTop, connectionPool), numberOfCakes,price, cupcakeId);
            List<CupCake> currentOrderList = new ArrayList<>();
            if(session.getAttribute("currentOrderList") != null){
                currentOrderList = (List<CupCake>) session.getAttribute("currentOrderList");
            }
            currentOrderList.add(tempCupCake);


            session.setAttribute("currentOrderList", currentOrderList);
            request.getRequestDispatcher("ordering.jsp").forward(request, response);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

}