/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAO;

import mvc.Koneksi.Koneksi;
import mvc.Model.Siswa;
import mvc.DAOInterface.ISiswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class DAOSiswa implements ISiswa {
    Connection connection;
    final String insert = "INSERT INTO siswa (nis, username, password, nama, tanggalLahir, alamat, noTelp, email, kelasId, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    final String update = "UPDATE siswa set username=?, password=?, nama=?, tanggalLahir=?, alamat=?, noTelp=?, email=?, kelasId=?, isActive=? where nis=?;";
    final String delete = "DELETE FROM siswa where nis=?;";
    final String select = "SELECT * FROM siswa;";
    final String carisiswa = "SELECT * FROM siswa WHERE nama like ?";
    
    public DAOSiswa() {
        connection = Koneksi.connection();
    }
    
    @Override
    public void insert(Siswa s) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, s.getNis());
            statement.setString(2, s.getUsername());
            statement.setString(3, s.getPassword());
            statement.setString(4, s.getNama());
            statement.setString(5, s.getTanggalLahir());
            statement.setString(6, s.getAlamat());
            statement.setString(7, s.getNoTelp());
            statement.setString(8, s.getEmail());
            statement.setString(9, s.getKelasId());
            statement.setString(10, s.getIsActive());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Gagal insert siswa: " + ex.getMessage());
        }
    }
    
    @Override
    public void update(Siswa s) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, s.getUsername());
            statement.setString(2, s.getPassword());
            statement.setString(3, s.getNama());
            statement.setString(4, s.getTanggalLahir());
            statement.setString(5, s.getAlamat());
            statement.setString(6, s.getNoTelp());
            statement.setString(7, s.getEmail());
            statement.setString(8, s.getKelasId());
            statement.setString(9, s.getIsActive());
            statement.setString(10, s.getNis());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Gagal update siswa: " + ex.getMessage());
        }
    }
    
    @Override
    public void delete(String nis) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, nis);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Gagal delete siswa: " + ex.getMessage());
        }
    }
    
    @Override
    public List<Siswa> getAll() {
        List<Siswa> listSiswa = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Siswa siswa = new Siswa();
                siswa.setNis(rs.getString("nis"));
                siswa.setUsername(rs.getString("username"));
                siswa.setPassword(rs.getString("password"));
                siswa.setNama(rs.getString("nama"));
                siswa.setTanggalLahir(rs.getString("tanggalLahir"));
                siswa.setAlamat(rs.getString("alamat"));
                siswa.setNoTelp(rs.getString("noTelp"));
                siswa.setEmail(rs.getString("email"));
                siswa.setKelasId(rs.getString("kelasId"));
                siswa.setIsActive(rs.getString("isActive"));
                listSiswa.add(siswa);
            }
        } catch (SQLException ex) {
            System.out.println("Gagal mengambil data siswa: " + ex.getMessage());
        }
        return listSiswa;
    }
    
    @Override
    public List<Siswa> getCariSiswa(String nama) {
        List<Siswa> listSiswa = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(carisiswa);
            st.setString(1, "%" + nama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Siswa siswa = new Siswa();
                siswa.setNis(rs.getString("nis"));
                siswa.setUsername(rs.getString("username"));
                siswa.setPassword(rs.getString("password"));
                siswa.setNama(rs.getString("nama"));
                siswa.setTanggalLahir(rs.getString("tanggalLahir"));
                siswa.setAlamat(rs.getString("alamat"));
                siswa.setNoTelp(rs.getString("noTelp"));
                siswa.setEmail(rs.getString("email"));
                siswa.setKelasId(rs.getString("kelasId"));
                siswa.setIsActive(rs.getString("isActive"));
                listSiswa.add(siswa);
            }
        } catch (SQLException ex) {
            System.out.println("Gagal mencari data siswa: " + ex.getMessage());
        }
        return listSiswa;
    }
    
    /**
     * Authenticate siswa credentials
     * 
     * @param username Siswa username
     * @param password Siswa password
     * @return Siswa object if authentication successful, null otherwise
     */
    public Siswa login(String username, String password) {
        Siswa siswa = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT * FROM siswa WHERE username = ? AND password = ? AND isActive = 'aktif'";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            
            rs = statement.executeQuery();
            
            if (rs.next()) {
                siswa = new Siswa();
                siswa.setNis(rs.getString("nis"));
                siswa.setUsername(rs.getString("username"));
                siswa.setNama(rs.getString("nama"));
                siswa.setTanggalLahir(rs.getString("tanggalLahir"));
                siswa.setAlamat(rs.getString("alamat"));
                siswa.setNoTelp(rs.getString("noTelp"));
                siswa.setEmail(rs.getString("email"));
                siswa.setKelasId(rs.getString("kelasId"));
                siswa.setIsActive(rs.getString("isActive"));
            }
            
            rs.close();
            statement.close();
            
        } catch (SQLException e) {
            System.out.println("Error in siswa login: " + e.getMessage());
        }
        
        return siswa;
    }
}
