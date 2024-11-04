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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Employee;
import model.accesscontroller.Department;
import model.accesscontroller.User;

/**
 *
 * @author acer giangvt
 */
public class EmployeeCreateController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        DepartmentDBContext db = new DepartmentDBContext();
        ArrayList<Department> depts = db.list();
        req.setAttribute("depts", depts);
        req.getRequestDispatcher("../view/employee/create.jsp").forward(req, resp);

    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        // Read parameters
        String raw_name = req.getParameter("name");
        String raw_gender = req.getParameter("gender");
        String raw_dob = req.getParameter("dob");
        String raw_address = req.getParameter("address");
        String raw_did = req.getParameter("d_id");

        // Validate parameters
        if (raw_name == null || raw_name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        Date dob = (raw_dob != null && !raw_dob.isBlank()) ? Date.valueOf(raw_dob) : null;
        if (dob == null) {
            throw new IllegalArgumentException("Date of Birth is invalid.");
        }

        Boolean gender = raw_gender != null && raw_gender.equals("male");

        if (raw_did == null || !raw_did.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid department ID.");
        }

        Department dept = new Department(Integer.parseInt(raw_did), null);

        // Object binding
        Employee e = new Employee();
        e.setName(raw_name);
        e.setGender(gender);
        e.setAddress(raw_address);
        e.setDob(dob);
        e.setCreatedby(logged);
        e.setDept(dept);

        // Save data to database
        EmployeeDBContext db = new EmployeeDBContext();
        db.insert(e);

        resp.sendRedirect("list");

    }

}
