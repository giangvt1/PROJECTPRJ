package controller.plan;

import dal.PlanDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import model.Plan;

public class ProductionPlanListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PlanDBContext db = new PlanDBContext(); // Sử dụng PlanDBContext để lấy kế hoạch
        ArrayList<Plan> plans = db.list(); // Lấy danh sách kế hoạch
        System.out.println("Total plans retrieved: " + plans.size()); // Log số lượng kế hoạch
        request.setAttribute("plans", plans); // Thiết lập thuộc tính cho JSP
        request.getRequestDispatcher("../view/productionplan/list.jsp").forward(request, response);
    }
}
