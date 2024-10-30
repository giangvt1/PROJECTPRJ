package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Thay thế cho java.beans.Statement
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Plan;
import model.PlanCampaign;
import model.Product;
import model.accesscontroller.Department;

public class PlanDBContext extends DBContext<Plan> {

    @Override
    public void insert(Plan model) {
        String sql_insert_plan = """
                             INSERT INTO [Plan] (startDate, endDate, d_id) 
                             VALUES (?, ?, ?)
                             """;
        String sql_insert_campaign = """
                                INSERT INTO [PlanCampaign] (p_id, pro_id, quantity, estimatedeffort) 
                                VALUES (?, ?, ?, ?)
                                """;
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan, Statement.RETURN_GENERATED_KEYS);
            stm_insert_plan.setDate(1, model.getStart());
            stm_insert_plan.setDate(2, model.getEnd());
            stm_insert_plan.setInt(3, model.getDept().getId());
            stm_insert_plan.executeUpdate();

            ResultSet rs = stm_insert_plan.getGeneratedKeys();
            int planId = -1;
            if (rs.next()) {
                planId = rs.getInt(1);
            }
            model.setId(planId);
            System.out.println("Inserted plan with ID: " + planId); // Debug info

            for (PlanCampaign campaign : model.getCampaigns()) {
                PreparedStatement stm_insert_campaign = connection.prepareStatement(sql_insert_campaign);
                stm_insert_campaign.setInt(1, planId);
                stm_insert_campaign.setInt(2, campaign.getProduct().getId());
                stm_insert_campaign.setInt(3, campaign.getQuantity());
                stm_insert_campaign.setFloat(4, campaign.getEstimatedeffort());
                stm_insert_campaign.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, "Error inserting plan", ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, "Error during rollback", e);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, "Error resetting auto-commit", ex);
            }
        }
    }

    public boolean isPlanCreated(int planId) {
        String sql = "SELECT COUNT(*) AS count FROM [Plan] WHERE p_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, planId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                System.out.println("Plan ID " + planId + " exists: " + (count > 0)); // Debug info
                return count > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, "Error checking plan existence", ex);
        }
        return false;
    }

    @Override
    public void update(Plan model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Plan model) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Plan> list() {
        ArrayList<Plan> plans = new ArrayList<>();
        String sql = """
        SELECT pc.pc_id, p.p_id, p.startDate, p.endDate, pro.pro_id, pro.pro_name, pc.estimatedeffort
                         FROM [Plan] p
                         LEFT JOIN PlanCampaign pc ON pc.p_id = p.p_id
                          JOIN [Product] pro ON pro.pro_id = pc.pro_id
        """;
        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                int planId = rs.getInt("p_id");
                Plan plan = plans.stream().filter(p -> p.getId() == planId).findFirst().orElse(null);
                if (plan == null) {
                    plan = new Plan();
                    plan.setId(planId);
                    plan.setStart(rs.getDate("startDate"));
                    plan.setEnd(rs.getDate("endDate"));
                    plans.add(plan);
                }
                int campaignId = rs.getInt("pc_id");
                if (campaignId > 0) {
                    PlanCampaign planCampaign = new PlanCampaign();
                    planCampaign.setId(campaignId);
                    planCampaign.setEstimatedeffort(rs.getFloat("estimatedeffort"));
                    Product product = new Product();
                    product.setId(rs.getInt("pro_id"));
                    product.setName(rs.getString("pro_name"));
                    planCampaign.setProduct(product);
                    plan.getCampaigns().add(planCampaign);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, "Error fetching plans", ex);
        }
        System.out.println("Total plans fetched: " + plans.size());
        return plans;
    }

    @Override
    public Plan get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
