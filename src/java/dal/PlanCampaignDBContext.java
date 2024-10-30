/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.PlanCampaign;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Plan;
import model.Product;

/**
 *
 * @author acer
 */
public class PlanCampaignDBContext extends DBContext<PlanCampaign> {

    @Override
    public void insert(PlanCampaign model) {

    }

    @Override
    public void update(PlanCampaign model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(PlanCampaign model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PlanCampaign> list() {
        ArrayList<PlanCampaign> campaigns = new ArrayList<>();
        String sql = """
        SELECT TOP (1000) pc.pc_id, pc.p_id, pc.pro_id, pc.quantity, pc.estimatedeffort, 
               p.startDate, p.endDate, pro.pro_name
        FROM [PlanCampaign] pc
        LEFT JOIN [Plan] p ON pc.p_id = p.p_id
        LEFT JOIN [Product] pro ON pc.pro_id = pro.pro_id
    """;

        try (PreparedStatement stm = connection.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                PlanCampaign campaign = new PlanCampaign();
                campaign.setId(rs.getInt("pc_id"));
                campaign.setQuantity(rs.getInt("quantity"));
                campaign.setEstimatedeffort(rs.getFloat("estimatedeffort"));

                // Set Product details
                Product product = new Product();
                product.setId(rs.getInt("pro_id"));
                product.setName(rs.getString("pro_name"));
                campaign.setProduct(product);

                // Set Plan details
                Plan plan = new Plan();
                plan.setId(rs.getInt("p_id"));
                plan.setStart(rs.getDate("startDate"));
                plan.setEnd(rs.getDate("endDate"));
                campaign.setPlan(plan);

                campaigns.add(campaign);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanCampaignDBContext.class.getName()).log(Level.SEVERE, "Error fetching PlanCampaigns", ex);
        }
        return campaigns;
    }

    @Override
    public PlanCampaign get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
