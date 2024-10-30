/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Employee;
import java.sql.*;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.accesscontroller.Department;
import model.accesscontroller.User;

/**
 *
 * @author acer
 */
public class EmployeeDBContext extends DBContext<Employee> {

    public ArrayList<Employee> search(Integer id, String name, Boolean gender, Date from, Date to, String address, Department dept) {
        String sql = """
                 SELECT e.e_id
                       ,e.e_name
                       ,e.gender
                       ,e.dob
                       ,e.[address]
                       ,e.d_id
                       ,d.d_name
                 FROM Employee e
                 JOIN Department d ON e.d_id = d.d_id
                 WHERE (1=1)""";

        ArrayList<Employee> emps = new ArrayList<>();
        ArrayList<Object> paramValues = new ArrayList<>();

        // Building the dynamic SQL query
        if (id != null) {
            sql += " AND e.e_id = ?";
            paramValues.add(id);
        }
        if (name != null && !name.isEmpty()) {
            sql += " AND e.e_name LIKE ?";
            paramValues.add("%" + name + "%");  // Wildcards handled here
        }
        if (gender != null) {
            sql += " AND e.gender = ?";
            paramValues.add(gender);
        }
        if (from != null) {
            sql += " AND e.dob >= ?";
            paramValues.add(from);
        }
        if (to != null) {
            sql += " AND e.dob <= ?";
            paramValues.add(to);
        }
        if (address != null && !address.isEmpty()) {
            sql += " AND e.[address] LIKE ?";
            paramValues.add("%" + address + "%");  // Wildcards handled here
        }
        if (dept != null) {
            sql += " AND d.d_id = ?";
            paramValues.add(dept.getId());
        }

        // Using try-with-resources for automatic resource management
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            for (int i = 0; i < paramValues.size(); i++) {
                stm.setObject((i + 1), paramValues.get(i));
            }

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Employee e = new Employee();
                    e.setId(rs.getInt("e_id"));
                    e.setName(rs.getNString("e_name"));
                    e.setGender(rs.getBoolean("gender"));
                    e.setAddress(rs.getNString("address"));
                    e.setDob(rs.getDate("dob"));

                    Department d = new Department();
                    d.setId(rs.getInt("d_id"));
                    d.setName(rs.getNString("d_name"));
                    e.setDept(d);

                    emps.add(e);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, "SQL Exception", ex);
        }

        return emps;
    }

    @Override
    public void insert(Employee model) {
        String sql_insert = """
                        insert into [Employee](e_name, gender, dob, [address], d_id, createdby)
                        values (?,?,?,?,?,?)""";
        String sql_getid = "Select SCOPE_IDENTITY() as e_id";
        PreparedStatement stm_ins = null;
        PreparedStatement stm_getid = null;
        ResultSet rs = null;

        try {
            connection.setAutoCommit(false);

            // Insert employee record
            stm_ins = connection.prepareStatement(sql_insert);
            stm_ins.setNString(1, model.getName());
            stm_ins.setBoolean(2, model.isGender());
            stm_ins.setDate(3, model.getDob());
            stm_ins.setNString(4, model.getAddress());
            stm_ins.setInt(5, model.getDept().getId());
            stm_ins.setString(6, model.getCreatedby().getUsername());
            stm_ins.executeUpdate();

            // Retrieve generated employee ID
            stm_getid = connection.prepareStatement(sql_getid);
            rs = stm_getid.executeQuery();
            if (rs != null && rs.next()) {
                model.setId(rs.getInt("e_id"));
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {

                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm_ins != null) {
                    stm_ins.close();
                }
                if (stm_getid != null) {
                    stm_getid.close();
                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Employee model) {
        String sql = """
                     UPDATE [Employee]
                        SET [e_name] = ?,
                            [gender] = ?,
                            [dob] = ?,
                            [address] = ?,
                            [d_id] = ?,
                            [updatedby] = ?,
                            [updatetime] = GETDATE() 
                      WHERE [e_id] = ?""";

        PreparedStatement stm_update = null;
        try {
            stm_update = connection.prepareStatement(sql);

            // Setting values for placeholders
            stm_update.setString(1, model.getName());
            stm_update.setBoolean(2, model.isGender());
            stm_update.setDate(3, model.getDob());
            stm_update.setString(4, model.getAddress());
            stm_update.setInt(5, model.getDept().getId());
            stm_update.setString(6, model.getUpdatedby().getUsername());
            stm_update.setInt(7, model.getId());  // WHERE clause for employee ID

            // Execute the update query
            stm_update.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm_update != null) {
                    stm_update.close();
                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Employee model
    ) {
        ArrayList<Employee> emps = new ArrayList<>();
        PreparedStatement stm_update = null;
        String sql = """
                         DELETE FROM Employee
                          WHERE e_id = ?""";
        try {
            stm_update = connection.prepareStatement(sql);
            stm_update.setInt(1, model.getId());
            stm_update.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                connection.close();

            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Employee> list() {
        ArrayList<Employee> emps = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = """
                    SELECT TOP (1000) [e_id]
                          ,[e_name]
                          ,[gender]
                          ,[dob]
                          ,[address]
                      FROM [SE1898_Student].[dbo].[Employee]""";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("e_id"));
                e.setName(rs.getNString("e_name"));
                e.setGender(rs.getBoolean("gender"));
                e.setDob(rs.getDate("dob"));
                e.setAddress(rs.getString("address"));

                emps.add(e);

            }
        } catch (SQLException e) {
            Logger.getLogger(EmployeeDBContext.class
                    .getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();

                }
            } catch (SQLException e) {
                Logger.getLogger(EmployeeDBContext.class
                        .getName()).log(Level.SEVERE, null, e);
            }
        }
        return emps;
    }

    @Override
    public Employee get(int id
    ) {
        PreparedStatement stm = null;
        ResultSet rs = null;  // Add this to ensure closing in the end
        try {
            String sql = """
                     SELECT e.e_id, e.e_name, e.gender, e.dob, e.[address], e.createdby, u.displayname as 'creator', 
                            e.updatedby, c.displayname as 'updator', e.updatetime, d.d_id, d.d_name
                     FROM [Employee] e
                     JOIN [User] u ON e.createdby = u.username
                     JOIN Department d ON d.d_id = e.d_id
                     LEFT JOIN [User] c ON c.username = e.updatedby
                     WHERE e.e_id = ?
                     """;
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();

            if (rs.next()) {
                System.out.println("Employee found with id: " + id);  // Debugging

                Employee e = new Employee();
                e.setId(rs.getInt("e_id"));
                e.setName(rs.getNString("e_name"));
                e.setGender(rs.getBoolean("gender"));
                e.setDob(rs.getDate("dob"));
                e.setAddress(rs.getNString("address"));
                e.setUpdatetime(rs.getTimestamp("updatetime"));

                User creator = new User();
                creator.setUsername(rs.getString("createdby"));
                creator.setDisplayname(rs.getString("creator"));
                e.setCreatedby(creator);

                User updator = new User();
                updator.setUsername(rs.getString("updatedby"));
                updator.setDisplayname(rs.getString("updator"));
                e.setUpdatedby(updator);

                Department d = new Department();
                d.setId(rs.getInt("d_id"));
                d.setName(rs.getNString("d_name"));
                e.setDept(d);

                return e;
            } else {
                System.out.println("No employee found with id: " + id);  // Debugging

            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();

                }
                // Do not close connection here unless it's supposed to be closed after every query
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
