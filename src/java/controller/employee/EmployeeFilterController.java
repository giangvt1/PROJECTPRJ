/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.employee;

import controller.accesscontrol.BaseRBACController;
import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.accesscontroller.User;
import java.sql.*;
import java.util.ArrayList;
import model.Employee;
import model.accesscontroller.Department;

/**
 *
 * @author acer giangvt
 */
public class EmployeeFilterController extends BaseRBACController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String raw_id = request.getParameter("id");
        String raw_name = request.getParameter("name");
        String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");
        String raw_gender = request.getParameter("gender");
        String raw_address = request.getParameter("address");
        String raw_did = request.getParameter("d_id");

        Integer id = raw_id != null && !raw_id.isBlank() ? Integer.parseInt(raw_id) : null;
        String name = raw_name;
        Date from = raw_from != null && !raw_from.isBlank() ? Date.valueOf(raw_to) : null;
        Date to = raw_to != null && !raw_to.isBlank() ? Date.valueOf(raw_to) : null;
        Boolean gender = (raw_gender == null || raw_gender.isBlank() || raw_gender.equals("both")) ? null : raw_gender.equals("male");
        String address = raw_address;
        Department dept = (raw_did != null) && (!raw_did.equals("-1")) ? new Department(Integer.parseInt(raw_did), null) : null;

        EmployeeDBContext db = new EmployeeDBContext();
        ArrayList<Employee> emps = db.search(id, name, gender, from, to, address, dept);
        DepartmentDBContext dbdept = new DepartmentDBContext();
        ArrayList<Department> depts = dbdept.list();
        request.setAttribute("emps", emps);
        request.setAttribute("depts", depts);
        request.getRequestDispatcher("../view/employee/filter.jsp").forward(request, response);
    }



    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
