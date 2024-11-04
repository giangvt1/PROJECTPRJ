package controller.plan;

import controller.accesscontrol.BaseRBACController;
import dal.PlanDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import model.Plan;
import model.accesscontroller.User;

public class ProductionPlanListController extends BaseRBACController {


    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User logged) throws ServletException, IOException {
        PlanDBContext db = new PlanDBContext(); // Sử dụng PlanDBContext để lấy kế hoạch
        ArrayList<Plan> plans = db.list(); // Lấy danh sách kế hoạch
        System.out.println("Total plans retrieved: " + plans.size()); // Log số lượng kế hoạch
        request.setAttribute("plans", plans); // Thiết lập thuộc tính cho JSP
        request.getRequestDispatcher("../view/productionplan/list.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User logged) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
