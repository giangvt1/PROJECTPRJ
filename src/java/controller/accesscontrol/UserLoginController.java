package controller.accesscontrol;

import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import model.accesscontroller.Feature;
import model.accesscontroller.Role;
import model.accesscontroller.User;

public class UserLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDBContext db = new UserDBContext();
        User account = db.get(username, password); // Fetch user account by username and password

        if (account != null) {
            // Save user account to session
            request.getSession().setAttribute("account", account);

            // Get user roles and features
            ArrayList<Role> roles = db.getRoles(username);
            account.setRoles(roles); // Ensure the User class has a setRoles method

            // Save allowed URLs for this user in the session
            request.getSession().setAttribute("allowedUrls", getAllowedUrls(roles));

            // Redirect to the navigation page
            response.sendRedirect(request.getContextPath() + "/view/login/navigation.jsp");
        } else {
            // If authentication fails
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("view/login/login.jsp").forward(request, response);
        }
    }

    // Generate allowed URLs based on user roles
    private Set<String> getAllowedUrls(ArrayList<Role> roles) {
        Set<String> allowedUrls = new HashSet<>();
        for (Role role : roles) {
            for (Feature feature : role.getFeatures()) {
                allowedUrls.add(feature.getUrl()); // Add the feature URL to the set
            }
        }
        return allowedUrls;
    }
}
