/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAO;
import mvc.Koneksi.Koneksi;
import mvc.Model.Admin;
import mvc.DAOInterface.IAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gansp
 */
public class DAOAdmin implements IAdmin {
    Connection connection;

    final String insert = "INSERT INTO admin (adminId, username, password, nama, email, noTelp, role, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    final String update = "UPDATE admin SET adminId=?, username=?, password=?, nama=?, email=?, noTelp=?, role=?, isActive=? WHERE id=?;";
    final String delete = "DELETE FROM admin WHERE id=?;";
    final String select = "SELECT * FROM admin;";
    final String carinamaadmin = "SELECT * FROM admin WHERE username LIKE ?;";
    
    
     public DAOAdmin() {
        connection = Koneksi.connection();
    }

    @Override
    public void insert(Admin b) {
        PreparedStatement statement = null;

    try {
        statement = connection.prepareStatement(insert);
        statement.setString(1, b.getAdminId());
        statement.setString(2, b.getUsername());
        statement.setString(3, b.getPassword());
        statement.setString(4, b.getNama());
        statement.setString(5, b.getEmail());
        statement.setString(6, b.getNoTelp());
        statement.setString(7, b.getRole());
        statement.setString(8, b.getIsActive());
       
        statement.executeUpdate();



    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } finally {
        try {
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Gagal Input");
        }
    }
    }

    @Override
    public void update(Admin b) {
        PreparedStatement statement = null;

    try {
        statement = connection.prepareStatement(update);
        statement.setString(1, b.getAdminId());
        statement.setString(2, b.getUsername());
        statement.setString(3, b.getPassword());
        statement.setString(4, b.getNama());
        statement.setString(5, b.getEmail());
        statement.setString(6, b.getNoTelp());
        statement.setString(7, b.getRole());
        statement.setString(8, b.getIsActive());
        statement.setInt(9, b.getId());

        statement.executeUpdate();

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } finally {
        try {
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Gagal Input");
        }
    }
 
}

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Berhasil Delete");
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Gagal Update");
            }
        }
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> lb = null;

    try {
        lb = new ArrayList<Admin>();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(select);

        while (rs.next()) {
            Admin b = new Admin();
            b.setId(rs.getInt("id"));
            b.setAdminId(rs.getString("adminId"));
            b.setEmail(rs.getString("email"));
            b.setUsername(rs.getString("username"));
            b.setPassword(rs.getString("password"));
            b.setNama(rs.getString("nama"));
            b.setNoTelp(rs.getString("noTelp"));
            b.setRole(rs.getString("role"));
            b.setIsActive(rs.getString("isActive"));
            lb.add(b);
        }

    } catch (SQLException ex) {
        Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
    
    }
    return lb;
    
    }
    

    
    @Override
    public List<Admin> getCariNama(String nama) {
        List<Admin> lb = null;

        try {
            lb = new ArrayList<Admin>();
            PreparedStatement st = connection.prepareStatement(carinamaadmin);
            st.setString(1, "%" + nama + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Admin b = new Admin();
                b.setId(rs.getInt("id"));
                b.setAdminId(rs.getString("adminId"));
                b.setUsername(rs.getString("username"));
                b.setPassword(rs.getString("password"));
                b.setNama(rs.getString("nama"));
                b.setEmail(rs.getString("email"));
                b.setNoTelp(rs.getString("noTelp"));
                b.setRole(rs.getString("role"));
                b.setIsActive(rs.getString("isActive"));
                lb.add(b);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lb;
    }
    
    /**
     * Authenticate admin credentials
     * 
     * @param username Admin username
     * @param password Admin password
     * @return Admin object if authentication successful, null otherwise
     */
    public Admin login(String username, String password) {
        Admin admin = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setAdminId(rs.getString("adminId"));
                admin.setUsername(rs.getString("username"));
                admin.setNama(rs.getString("nama"));
                admin.setEmail(rs.getString("email"));
                admin.setNoTelp(rs.getString("noTelp"));
                admin.setRole(rs.getString("role"));
                admin.setIsActive(rs.getString("isActive"));
            }
            
            rs.close();
            statement.close();
            
        } catch (SQLException e) {
            System.out.println("Error in admin login: " + e.getMessage());
        }
        
        return admin;
    }
}




