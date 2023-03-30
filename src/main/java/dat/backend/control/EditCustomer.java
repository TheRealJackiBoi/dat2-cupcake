package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditCustomer", value = "/editcustomer")
public class EditCustomer extends HttpServlet {
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = null;
        try {
            userList = UserFacade.getAllUsers(connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.setAttribute("userList", userList);

        request.getRequestDispatcher("WEB-INF/editcustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        float balance = Float.parseFloat(request.getParameter("balance"));
        int admin;
        User user = UserFacade.getUserByEmail(email, connectionPool);
        request.setAttribute("email", user);

        if  (request.getParameterMap().containsKey("admin")) {
            admin = 1;
        } else {
            admin = 0;
        }
        try {
            UserMapper.updateUser(email, balance, admin, connectionPool);
            user = UserFacade.getUserByEmail(email, connectionPool);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("admincustomers");
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
