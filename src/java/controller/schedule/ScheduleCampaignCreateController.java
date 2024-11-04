package controller.schedule;

import controller.accesscontrol.BaseRBACController;
import dal.ScheduleCampaignDBContext;
import dal.PlanDBContext;
import dal.PlanCampaignDBContext;
import model.ScheduleCampaign;
import model.PlanCampaign;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.accesscontroller.User;

public class ScheduleCampaignCreateController extends BaseRBACController {


    @Override
    protected void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        PlanCampaignDBContext planCampaignDB = new PlanCampaignDBContext();
        ArrayList<PlanCampaign> planCampaigns = planCampaignDB.list();

        // Debug: Log to check if planCampaigns list is populated
        if (planCampaigns.isEmpty()) {
            System.out.println("No PlanCampaigns found.");
        } else {
            System.out.println("PlanCampaigns found: " + planCampaigns.size());
        }

        req.setAttribute("planCampaigns", planCampaigns);
        req.getRequestDispatcher("../view/schedule/create.jsp").forward(req, resp);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        int pc_id = Integer.parseInt(req.getParameter("pc_id"));
        Date date = Date.valueOf(req.getParameter("date"));
        String shift = req.getParameter("shift");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        ScheduleCampaign scheduleCampaign = new ScheduleCampaign();
        PlanCampaign planCampaign = new PlanCampaign();
        planCampaign.setId(pc_id);
        scheduleCampaign.setCamps(planCampaign);
        scheduleCampaign.setDate(date);
        scheduleCampaign.setShift(shift);
        scheduleCampaign.setQuantity(quantity);

        ScheduleCampaignDBContext db = new ScheduleCampaignDBContext();
        db.insert(scheduleCampaign);

        // Set an attribute to indicate successful creation
        req.getSession().setAttribute("createSuccess", true);

        // Redirect to the list page
        resp.sendRedirect("list");
    }

}
