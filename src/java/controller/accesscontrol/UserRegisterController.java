package controller.accesscontrol;

import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.accesscontroller.User;

public class UserRegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/login/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String displayname = request.getParameter("displayname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setDisplayname(displayname);
        user.setGmail(email);
        user.setPhone(phone);

        UserDBContext db = new UserDBContext();
        boolean isUserCreated = db.create(user);
        if (isUserCreated) {
            response.sendRedirect(request.getContextPath() + "/view/login/login.jsp");
        } else {
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
            request.getRequestDispatcher("/view/login/registration.jsp").forward(request, response);
        }
    }

}
