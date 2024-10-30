package controller.schedule;

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

public class ScheduleCampaignCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Parse input data
        int pc_id = Integer.parseInt(req.getParameter("pc_id"));
        Date date = Date.valueOf(req.getParameter("date"));
        String shift = req.getParameter("shift");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        // Set data to ScheduleCampaign model
        ScheduleCampaign scheduleCampaign = new ScheduleCampaign();
        PlanCampaign planCampaign = new PlanCampaign();
        planCampaign.setId(pc_id);
        scheduleCampaign.setCamps(planCampaign);
        scheduleCampaign.setDate(date);
        scheduleCampaign.setShift(shift);
        scheduleCampaign.setQuantity(quantity);

        // Insert new ScheduleCampaign
        ScheduleCampaignDBContext db = new ScheduleCampaignDBContext();
        db.insert(scheduleCampaign);

        // Redirect or forward to a success page or list
        resp.sendRedirect("list"); // Assuming 'list' URL shows the list of ScheduleCampaigns
    }
}
