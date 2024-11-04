package controller.schedule;

import controller.accesscontrol.BaseRBACController;
import dal.ScheduleCampaignDBContext;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ScheduleCampaign;
import model.accesscontroller.User;

public class ScheduleCampaignListController extends BaseRBACController {


    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User logged) throws ServletException, IOException {
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
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User logged) throws ServletException, IOException {
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
