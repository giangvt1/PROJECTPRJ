/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.accesscontroller.User;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.accesscontroller.Department;
import model.accesscontroller.Feature;
import model.accesscontroller.Role;

/**
 *
 * @author acer
 */
public class UserDBContext extends DBContext<User> {

    public ArrayList<Employee> filter(Integer id, String name, Boolean gender, Date dob, String address, Department dept) {
        ArrayList<Employee> emps = new ArrayList<>();
        return emps;
    }

    public ArrayList<Role> getRoles(String username) {
        ArrayList<Role> roles = new ArrayList<>();
        String sql = """
                 SELECT r.r_id, r.r_name, f.f_id, f.f_name, f.url 
                 FROM [User] u
                 JOIN UserRole ur ON ur.username = u.username
                 JOIN [Role] r ON r.r_id = ur.r_id
                 JOIN RoleFeature rf ON rf.r_id = r.r_id
                 JOIN Feature f ON f.f_id = rf.f_id
                 WHERE u.username = ? 
                 ORDER BY f.f_id ASC""";

        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();

            Role currentRole = null; // Initialize current role
            while (rs.next()) {
                int r_id = rs.getInt("r_id");
                if (currentRole == null || r_id != currentRole.getId()) {
                    // If currentRole is null or we have a new role, create a new Role object
                    currentRole = new Role();
                    currentRole.setId(r_id);
                    currentRole.setName(rs.getString("r_name"));
                    currentRole.setFeatures(new ArrayList<>()); // Initialize features list
                    roles.add(currentRole);
                }
                // Create Feature object
                Feature feature = new Feature();
                feature.setId(rs.getInt("f_id"));
                feature.setName(rs.getString("f_name"));
                feature.setUrl(rs.getString("url"));
                currentRole.getFeatures().add(feature); // Add feature to the current role
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, e);
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
            } catch (SQLException e) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return roles; // Return the list of roles
    }

    /**
     * LOGIN WITHOUT PASSWORD Username='phuc' and password='' OR '1'='1
     *
     * @param username
     * @param password
     * @return
     */
    public User get(String username, String password) {
        String sql = """
                 SELECT [username]
                       ,[password]
                       ,[displayname]
                       ,[gmail]
                       ,[phone]
                 FROM [SE1898_Student].[dbo].[User]
                 WHERE username=? AND [password]=?
                 """;
        PreparedStatement stm = null;
        User user = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setDisplayname(rs.getString("displayname"));
                user.setUsername(username);
                user.setGmail(rs.getString("gmail"));
                user.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return user;
    }

    @Override
    public void insert(User model) {
        String sql_insert = """
                        insert into [User](username, password, displayname, gmail, phone)
                        values (?,?,?,?,?)""";
        try (PreparedStatement stm = connection.prepareStatement(sql_insert)) {
            connection.setAutoCommit(false);
            stm.setString(1, model.getUsername());
            stm.setString(2, model.getPassword());
            stm.setString(3, model.getDisplayname());
            stm.setString(4, model.getGmail());
            stm.setString(5, model.getPhone());
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException e) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, e);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean create(User user) {
        insert(user);
        return true;
    }

    @Override
    public void update(User model
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
