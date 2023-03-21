package dat.backend.model.services;

import dat.backend.model.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication
{
    public static boolean isRoleAllowed(String role, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null)
        {
            return user.getRole().equals(role);
        }
        return false;
    }
}
