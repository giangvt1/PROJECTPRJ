package controller.schedule;

import dal.ScheduleCampaignDBContext;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ScheduleCampaign;

public class ScheduleCampaignListController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String planIdParam = request.getParameter("planId");
        
        if (planIdParam == null || planIdParam.isEmpty()) {
            request.setAttribute("errorMessage", "Plan ID is required.");
            request.getRequestDispatcher("../view/schedule/list.jsp").forward(request, response);
            return;
        }
        
        int planId = Integer.parseInt(planIdParam);
        ScheduleCampaignDBContext db = new ScheduleCampaignDBContext();
        List<ScheduleCampaign> campaigns = db.list(planId);
        
        request.setAttribute("campaigns", campaigns);
        request.setAttribute("planId", planId);
        request.getRequestDispatcher("../view/schedule/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String planIdParam = request.getParameter("planId");
        
        if (planIdParam == null || planIdParam.isEmpty()) {
            request.setAttribute("errorMessage", "Plan ID is required.");
            request.getRequestDispatcher("../view/schedule/list.jsp").forward(request, response);
            return;
        }
        
        int planId = Integer.parseInt(planIdParam);
        ScheduleCampaignDBContext db = new ScheduleCampaignDBContext();
        List<ScheduleCampaign> campaigns = db.list(planId);
        
        request.setAttribute("campaigns", campaigns);
        request.setAttribute("planId", planId);
        request.getRequestDispatcher("../view/schedule/list.jsp").forward(request, response);
    }
}
