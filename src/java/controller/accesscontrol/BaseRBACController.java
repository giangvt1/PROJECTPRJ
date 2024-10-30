/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.accesscontrol;

import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.accesscontroller.Feature;
import model.accesscontroller.Role;
import model.accesscontroller.User;

/**
 *
 * @author acer
 */
public abstract class BaseRBACController extends BaseRequiredAuthentication {

    private boolean isAuthorized(HttpServletRequest req, User logged)
    {
        UserDBContext db = new UserDBContext();
        ArrayList<Role> roles = db.getRoles(logged.getUsername());
        logged.setRoles(roles);
        String c_url = req.getServletPath();
        for (Role role : roles) {
            for (Feature feature : role.getFeatures()) {
                if(feature.getUrl().equals(c_url))
                    return true;
            }
        }
        
        return false;
    }

    protected abstract void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException;

    protected abstract void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        if (isAuthorized(req, logged)) {
            doAuthorizedGet(req, resp, logged);
        } else {
            resp.sendError(403, "You do not have a right to access this page!!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        if (isAuthorized(req, logged)) {
            doAuthorizedPost(req, resp, logged);
        } else {
            resp.sendError(403, "You do not have a right to access this page!!!");
        }
    }
}
