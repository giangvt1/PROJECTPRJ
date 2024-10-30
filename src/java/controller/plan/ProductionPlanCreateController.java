package controller.plan;

import dal.DepartmentDBContext;
import dal.PlanDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Plan;
import model.PlanCampaign;
import model.Product;
import model.accesscontroller.Department;

public class ProductionPlanCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDBContext dbProduct = new ProductDBContext();
        DepartmentDBContext dbDept = new DepartmentDBContext();
        request.setAttribute("products", dbProduct.list());
        request.setAttribute("depts", dbDept.getByType(2));
        request.getRequestDispatcher("../view/productionplan/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy danh sách ID sản phẩm từ form
        String[] pids = request.getParameterValues("pro_id");
        if (pids == null || pids.length == 0) {
            response.getWriter().println("No products selected.");
            return;
        }

        // Lấy thông tin từ request
        String fromDate = request.getParameter("from");
        String toDate = request.getParameter("to");
        String deptId = request.getParameter("d_id");

        // Kiểm tra các trường bắt buộc
        if (fromDate == null || toDate == null || deptId == null || deptId.isEmpty()) {
            response.getWriter().println("Missing required fields for creating a plan.");
            return;
        }

        Plan plan = new Plan();
        try {
            plan.setStart(Date.valueOf(fromDate));
            plan.setEnd(Date.valueOf(toDate));
            Department department = new Department();
            department.setId(Integer.parseInt(deptId));
            plan.setDept(department);
            plan.setCampaigns(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Invalid date format.");
            return;
        }

        // Xử lý từng sản phẩm
        for (String pid : pids) {
            Product product = new Product();
            product.setId(Integer.parseInt(pid));
            PlanCampaign campaign = new PlanCampaign();
            campaign.setProduct(product);

            String rawQuantity = request.getParameter("quantity" + pid);
            String rawEffort = request.getParameter("effort" + pid);

            try {
                int quantity = (rawQuantity != null && !rawQuantity.isEmpty()) ? Integer.parseInt(rawQuantity) : 0;
                float effort = (rawEffort != null && !rawEffort.isEmpty()) ? Float.parseFloat(rawEffort) : 0;
                campaign.setQuantity(quantity);
                campaign.setEstimatedeffort(effort);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid quantity or effort value for product ID: " + pid);
                return;
            }

            campaign.setPlan(plan);
            if (campaign.getQuantity() != 0 && campaign.getEstimatedeffort() != 0) {
                plan.getCampaigns().add(campaign);
            }
        }

        // Lưu kế hoạch vào cơ sở dữ liệu
        if (plan.getCampaigns().size() > 0) {
            PlanDBContext db = new PlanDBContext();
            db.insert(plan);
            System.out.println("Inserted plan ID: " + plan.getId()); // Thông tin gỡ lỗi
            boolean isCreated = db.isPlanCreated(plan.getId());
            if (isCreated) {
                response.getWriter().println("Created a new plan!");
            } else {
                response.getWriter().println("Failed to create a new plan.");
            }
        } else {
            response.getWriter().println("Your plan did not have any campaigns.");
        }
    }
}
