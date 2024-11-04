/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import model.Attentdant;
import java.sql.*;
import model.Employee;
import model.ScheduleCampaign;
import model.WorkerSchedule;

/**
 *
 * @author acer
 */
public class AttendantDBContext extends DBContext<Attentdant> {

    @Override
    public void insert(Attentdant model) {
        int workerScheduleId = getOrCreateWorkerSchedule(model.getWs().getE().getId(), model.getWs().getSc().getId());

        String sql = "INSERT INTO Attendant (ws_id, alpha, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, workerScheduleId);
            statement.setFloat(2, model.getAlpha());
            statement.setInt(3, model.getQuantity());

            statement.executeUpdate();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AttendantDBContext.class.getName()).log(java.util.logging.Level.SEVERE, "Error inserting Attendant", ex);
        }
    }

    private int getOrCreateWorkerSchedule(int employeeId, int scheduleCampaignId) {
        String selectSQL = "SELECT ws_id FROM WorkerSchedule WHERE e_id = ? AND sc_id = ?";
        String insertSQL = "INSERT INTO WorkerSchedule (e_id, sc_id) VALUES (?, ?)";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSQL); PreparedStatement insertStatement = connection.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Check if the WorkerSchedule already exists
            selectStatement.setInt(1, employeeId);
            selectStatement.setInt(2, scheduleCampaignId);
            try (ResultSet rs = selectStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ws_id"); // Return existing ws_id if found
                }
            }

            // If not found, insert a new WorkerSchedule entry
            insertStatement.setInt(1, employeeId);
            insertStatement.setInt(2, scheduleCampaignId);
            insertStatement.executeUpdate();

            // Retrieve generated ws_id for the new entry
            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AttendantDBContext.class.getName()).log(java.util.logging.Level.SEVERE, "Error handling WorkerSchedule", ex);
        }
        return -1; // Return -1 if there's an issue
    }

    @Override
    public void update(Attentdant model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attentdant model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attentdant> list() {
        ArrayList<Attentdant> attendants = new ArrayList<>();
        String sql = """
        SELECT e.e_id, e.e_name, sc.date, a.quantity AS attendant_quantity, sc.quantity AS schedule_quantity, 
               a.alpha, a.alpha * 8 * 8000 * 30 AS Salary
        FROM Attendant a 
        LEFT JOIN WorkerSchedule ws ON ws.ws_id = a.ws_id
        LEFT JOIN Employee e ON e.e_id = ws.e_id
        LEFT JOIN ScheduleCampaign sc ON sc.sc_id = ws.sc_id
        """;

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Attentdant attendant = new Attentdant();
                attendant.setAlpha(rs.getFloat("alpha"));
                attendant.setQuantity(rs.getInt("attendant_quantity")); // Set Attendant quantity
                attendant.setSalary(rs.getFloat("Salary")); // Set calculated Salary

                // Create WorkerSchedule and link Employee and ScheduleCampaign
                WorkerSchedule ws = new WorkerSchedule();
                Employee employee = new Employee();
                employee.setId(rs.getInt("e_id")); // Set Employee ID
                employee.setName(rs.getString("e_name")); // Set Employee Name
                ws.setE(employee); // Link Employee to WorkerSchedule

                ScheduleCampaign sc = new ScheduleCampaign();
                sc.setDate(rs.getDate("date")); // Set the ScheduleCampaign date
                sc.setQuantity(rs.getInt("schedule_quantity")); // Set ScheduleCampaign quantity
                ws.setSc(sc); // Link ScheduleCampaign to WorkerSchedule

                attendant.setWs(ws); // Link WorkerSchedule to Attendant

                attendants.add(attendant); // Add to the list
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AttendantDBContext.class.getName()).log(java.util.logging.Level.SEVERE, "Error fetching attendants", ex);
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(AttendantDBContext.class.getName()).log(java.util.logging.Level.SEVERE, "Error closing resources", ex);
            }
        }
        return attendants;
    }

    @Override
    public Attentdant get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
