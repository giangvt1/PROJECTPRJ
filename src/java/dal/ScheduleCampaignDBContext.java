/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PlanCampaign;
import model.Product;
import model.ScheduleCampaign;
import java.sql.*;

/**
 *
 * @author acer
 */
public class ScheduleCampaignDBContext extends DBContext<ScheduleCampaign> {

    public ArrayList<ScheduleCampaign> list(int planId) {
        ArrayList<ScheduleCampaign> productionDetails = new ArrayList<>();

        String sql = "SELECT p.p_id, sc.[date], sc.K, pro.pro_name, sc.quantity "
                + "FROM [ScheduleCampaign] sc "
                + "LEFT JOIN PlanCampaign pc ON pc.pc_id = sc.pc_id "
                + "LEFT JOIN [Plan] p ON p.p_id = pc.p_id "
                + "JOIN [Product] pro ON pro.pro_id = pc.pro_id "
                + "WHERE p.p_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, planId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                ScheduleCampaign sc = new ScheduleCampaign();
                sc.setId(rs.getInt("p_id"));
                sc.setDate(rs.getDate("date"));
                sc.setShift(rs.getString("K"));

                // Set Product details in ScheduleCampaign
                Product product = new Product();
                product.setName(rs.getString("pro_name"));
                sc.setPros(product); // Assuming sc.setPros is the method to set the Product

                sc.setQuantity(rs.getInt("quantity"));

                productionDetails.add(sc);
            }
        } catch (SQLException e) {
            Logger.getLogger(ScheduleCampaignDBContext.class.getName()).log(Level.SEVERE, "Error fetching production details", e);
        }

        return productionDetails;
    }

    @Override
public void insert(ScheduleCampaign model) {
    String getMaxIdSql = "SELECT MAX(sc_id) AS max_id FROM ScheduleCampaign";
    String insertSql = "INSERT INTO ScheduleCampaign (sc_id, pc_id, date, K, quantity) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement ps = null;

    try {
        // Get the current maximum ID
        ps = connection.prepareStatement(getMaxIdSql);
        ResultSet rs = ps.executeQuery();
        int newId = 1; // Default to 1 if no rows exist

        if (rs.next()) {
            newId = rs.getInt("max_id") + 1;
        }
        rs.close();
        
        // Set the new ID to the model
        model.setId(newId);

        // Start transaction
        connection.setAutoCommit(false);

        // Prepare statement for ScheduleCampaign insert
        ps = connection.prepareStatement(insertSql);

        // Set parameters
        ps.setInt(1, newId); // Set the new ID
        ps.setInt(2, model.getCamps().getId());
        ps.setDate(3, model.getDate());
        ps.setString(4, model.getShift());
        ps.setInt(5, model.getQuantity());

        // Execute insert
        ps.executeUpdate();

        // Commit transaction
        connection.commit();
        System.out.println("ScheduleCampaign inserted successfully with ID: " + newId);

    } catch (SQLException e) {
        try {
            connection.rollback();
            Logger.getLogger(ScheduleCampaignDBContext.class.getName()).log(Level.SEVERE,
                    "Transaction rolled back due to an error: " + e.getMessage(), e);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleCampaignDBContext.class.getName()).log(Level.SEVERE, "Error during rollback", ex);
        }
    } finally {
        try {
            if (ps != null) {
                ps.close();
            }
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleCampaignDBContext.class.getName()).log(Level.SEVERE, "Error resetting auto-commit or closing PreparedStatement", ex);
        }
    }
}


    @Override
    public void update(ScheduleCampaign model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(ScheduleCampaign model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ScheduleCampaign> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ScheduleCampaign get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
