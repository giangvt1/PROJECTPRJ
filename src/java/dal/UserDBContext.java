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

    public void updateResetCode(String username, int code, LocalDateTime expiration) {
        String sql = "UPDATE [User] SET reset_code = ?, reset_code_expiration = ? WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, code);
            ps.setTimestamp(2, Timestamp.valueOf(expiration));
            ps.setString(3, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public ArrayList<Employee> filter(Integer id, String name, Boolean gender, Date dob, String address, Department dept) {
        ArrayList<Employee> emps = new ArrayList<>();
        return emps;
    }

    public ArrayList<Role> getRoles(String username) {
        ArrayList<Role> roles = new ArrayList<>();
        String sql = """
                     select r.r_id,r.r_name,f.f_id,f.f_name,f.url from [User] u
                     join UserRole ur on ur.username=u.username
                     join [Role] r on r.r_id=ur.r_id
                     join RoleFeature rf on rf.r_id=r.r_id
                     join Feature f on f.f_id=rf.f_id
                     where u.username=? 
                     order by f.f_id asc""";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            Role crole = new Role();
            crole.setId(-1);
            while (rs.next()) {
                int r_id = rs.getInt("r_id");
                if (r_id != crole.getId()) {
                    crole = new Role();
                    crole.setId(r_id);
                    crole.setName(rs.getString("r_name"));
                    roles.add(crole);
                }
                Feature f = new Feature();
                f.setId(rs.getInt("f_id"));
                f.setName(rs.getString("f_name"));
                f.setUrl(rs.getString("url"));
                crole.getFeatures().add(f);
                f.setRoles(roles);
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        return roles;
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
