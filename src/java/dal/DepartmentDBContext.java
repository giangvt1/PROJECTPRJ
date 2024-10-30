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
import model.accesscontroller.Department;

/**
 *
 * @author acer
 */
public class DepartmentDBContext extends DBContext<Department> {

    public ArrayList<Department> getByType(int type) {
        ArrayList<Department> depts = new ArrayList<>();
        String sql = "SELECT d_id, d_name, d_type FROM Department WHERE d_type = ?";

        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, type);  // Setting the department type
            rs = stm.executeQuery();

            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getInt("d_id"));
                d.setName(rs.getString("d_name"));
                d.setType(rs.getInt("d_type"));  // Retrieve department type as an integer
                depts.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
                Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return depts;
    }

    @Override
    public void insert(Department model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Department model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Department model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Department> list() {
        ArrayList<Department> depts = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT d_id, d_name FROM Department";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Department dept = new Department();
                dept.setId(rs.getInt("d_id"));
                dept.setName(rs.getString("d_name"));
                depts.add(dept);
            }

        } catch (SQLException e) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, e);

        } finally {
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
                Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return depts;
    }

    @Override
    public Department get(int d_id) {
        Department dept = null;
        String sql = "SELECT d_id, d_name, d_type FROM Department WHERE d_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, d_id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    dept = new Department();
                    dept.setId(rs.getInt("d_id"));
                    dept.setName(rs.getString("d_name"));
                    dept.setType(rs.getInt("d_type"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, e);
        }

        return dept;
    }

}
