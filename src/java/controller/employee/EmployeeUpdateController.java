package controller.employee;

import controller.accesscontrol.BaseRBACController;
import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;
import model.accesscontroller.Department;
import model.accesscontroller.User;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class EmployeeUpdateController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        String raw_id = req.getParameter("id");
        if (raw_id == null || raw_id.trim().isEmpty()) {
            req.setAttribute("error", "Employee ID is required.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        try {
            int id = Integer.parseInt(raw_id);
            EmployeeDBContext dbEmp = new EmployeeDBContext();
            Employee emp = dbEmp.get(id);

            if (emp != null) {
                DepartmentDBContext dbDept = new DepartmentDBContext();
                ArrayList<Department> depts = dbDept.list();

                req.setAttribute("employee", emp);
                req.setAttribute("depts", depts);
                req.getRequestDispatcher("../view/employee/update.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Employee does not exist.");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid Employee ID format.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        // Read parameters
        String raw_id = req.getParameter("id");
        String raw_name = req.getParameter("name");
        String raw_gender = req.getParameter("gender");
        String raw_dob = req.getParameter("dob");
        String raw_address = req.getParameter("address");
        String raw_did = req.getParameter("d_id");

        // Validate the ID parameter specifically
        int employeeId;
        try {
            employeeId = Integer.parseInt(raw_id);
        } catch (NumberFormatException e) {
            resp.getWriter().println("Invalid Employee ID: " + raw_id);
            return;
        }

        // Check other required fields
        if (raw_name == null || raw_name.trim().isEmpty()) {
            resp.getWriter().println("Name is required.");
            return;
        }
        if (raw_gender == null || (!raw_gender.equals("male") && !raw_gender.equals("female"))) {
            resp.getWriter().println("Valid gender is required.");
            return;
        }
        if (raw_dob == null || !isValidDate(raw_dob)) {
            resp.getWriter().println("Valid date of birth is required.");
            return;
        }
        if (raw_address == null || raw_address.trim().isEmpty()) {
            resp.getWriter().println("Address is required.");
            return;
        }
        if (raw_did == null || raw_did.trim().isEmpty()) {
            resp.getWriter().println("Department ID is required.");
            return;
        }

        try {
            // Parse department ID
            int departmentId = Integer.parseInt(raw_did);

            // Bind data to Employee object
            Employee e = new Employee();
            e.setId(employeeId);
            e.setName(raw_name);
            e.setGender(raw_gender.equals("male"));
            e.setDob(Date.valueOf(raw_dob));
            e.setAddress(raw_address);
            e.setUpdatedby(logged);

            Department dept = new Department();
            dept.setId(departmentId);
            e.setDept(dept);

            // Update the employee in the database
            EmployeeDBContext db = new EmployeeDBContext();
            db.update(e);

            // Redirect to avoid resubmission
            resp.sendRedirect(req.getContextPath() + "/employee/list"); // Redirect to the employee list page or another relevant page

        } catch (NumberFormatException ex) {
            resp.getWriter().println("Invalid Department ID: " + raw_did);
        } catch (IOException ex) {
            resp.getWriter().println("An error occurred: " + ex.getMessage());
        }
    }

    private boolean isValidDate(String date) {
        try {
            Date.valueOf(date);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
