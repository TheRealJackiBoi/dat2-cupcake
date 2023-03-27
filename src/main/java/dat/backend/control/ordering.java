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
        if(user == null){
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
        try {
            List<BottomCake> bottomCakeList = BottomCakeFacade.getAllBottoms(connectionPool);
            List<TopCake> topCakeList = TopCakeFacade.getAllTops(connectionPool);

            request.setAttribute("bottomCakeList", bottomCakeList);
            request.setAttribute("topCakeList", topCakeList);

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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String email = user.getUsername();

        try {
            int newOrderId = OrderFacade.addOrder(user,email,connectionPool);
            float price = CupCakeFacade.calculatePrice(cupcakeBottom,cupcakeTop,numberOfCakes,connectionPool);
            int cupcakeId = CupCakeFacade.addCupCake(numberOfCakes,newOrderId,cupcakeBottom,cupcakeTop,price, connectionPool);

            List<CupCake> cupCakeList = CupCakeFacade.getCakesByOrderId(newOrderId,connectionPool);
            request.setAttribute("cupCakeList", cupCakeList);
            request.getRequestDispatcher("ordering.jsp").forward(request,response);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }

    }

}