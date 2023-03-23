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

@WebServlet(name = "signUpPage", urlPatterns = {"/signUpPage"})
public class SignUpServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException{
        this.connectionPool = ApplicationStart.getConnectionPool();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        UserMapper um = new UserMapper();
        User user;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = "user";
        try{
            user = UserMapper.createUser(email, password, role, connectionPool);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }



    }
}
