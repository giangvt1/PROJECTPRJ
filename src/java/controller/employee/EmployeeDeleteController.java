package controller.employee;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import controller.accesscontrol.BaseRBACController;
import controller.accesscontrol.BaseRequiredAuthentication;
import dal.EmployeeDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;
import model.accesscontroller.User;

/**
 *
 * @author acer giangvt
 */
public class EmployeeDeleteController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        resp.sendError(404, "You do not have right to accsess this page");
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        EmployeeDBContext db = new EmployeeDBContext();
        Employee model = new Employee();
        model.setId(id);
        db.delete(model);
        resp.sendRedirect("list");
    }

}
