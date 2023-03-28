package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupCakeFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveFromOrder", value = "/removefromorder")
public class RemoveFromOrder extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cupcakeId = Integer.parseInt(request.getParameter("removecupcake"));

        try {
            int removeCupCake = CupCakeFacade.removeCupCake(cupcakeId, connectionPool);
            //would be best if it had a check to see if there were ==1 lines affected by this update
            if(removeCupCake == 1) {
                //virker "delvist" fjerne hele ordren, men beholder at man skal betale 35kr for nul, derudover fjerner det ikke noget fra SQL
                response.sendRedirect("ordering");
            }
            else {
                request.setAttribute("errormessage", "there was an error when trying to remove the cupcake, we are sorry about that");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
