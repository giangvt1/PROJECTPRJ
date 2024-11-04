package controller.plan;

import controller.accesscontrol.BaseRBACController;
import dal.PlanDBContext;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DetailPlan;
import model.accesscontroller.User;

public class DetailPlanController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        // Fetch details from PlanDBContext
        PlanDBContext planDB = new PlanDBContext();
        ArrayList<DetailPlan> details = planDB.getDetail();

        // Set the details as a request attribute to be accessed in JSP
        req.setAttribute("details", details);

        // Forward the request to the JSP page to display the details
        req.getRequestDispatcher("../view/productionplan/detail.jsp").forward(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        // For now, we’re not implementing POST functionality
        throw new UnsupportedOperationException("POST is not supported yet.");
    }
}
