/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAO;

import mvc.Koneksi.Koneksi;
import mvc.Model.Kelas;
import mvc.DAOInterface.IKelas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc.Model.Guru;
/**
 *
 * @author nabig
 */
public class DAOKelas implements IKelas{
    Connection connection;
    final String insert = "INSERT INTO kelas(kelasId, namaKelas, nipWaliKelas, tahunAjaran, capacity) VALUES (?, ?, ?, ?, ?);";
    final String update = "UPDATE kelas set kelasId=?, namaKelas=?, nipWaliKelas=?, tahunAjaran=?, cepacity=? where id=? ;";
    final String delete = "DELETE FROM kelas where id=? ;";
    final String select = "SELECT * FROM kelas;";
    final String carinamakelas = "SELECT * FROM kelas where namaKelas like ?";
    final String selectguru = "SELECT * FROM guru";
    
    public DAOKelas(){
        connection = Koneksi.connection();
    }

    @Override
    public void insert(Kelas b) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getKelasId());
            statement.setString(2, b.getNamaKelas());
            statement.setString(3, b.getNipWaliKelas());
            statement.setInt(4, b.getTahunAjaran());
            statement.setInt(5, b.getCapacity());
            statement.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Berhasil Input");
        }finally {
            try{
                statement.close();
            }catch (SQLException ex){
                System.out.println("Gagal Input");
            }
        }
    }

    @Override
    public void update(Kelas b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getKelasId());
            statement.setString(2, b.getNamaKelas());
            statement.setString(3, b.getNipWaliKelas());
            statement.setInt(4, b.getTahunAjaran());
            statement.setInt(5, b.getCapacity());
            statement.setInt(6, b.getId());
            statement.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Gagal Update");
        }finally {
            try {
                statement.close();
            }catch (SQLException ex){
                System.out.println("Gagal Input");
            }
        }
        
    }

    @Override
    public void delete(int kelasId) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setInt(1, kelasId);
            statement.execute();
        }catch (SQLException ex){
            System.out.println("Berhasil Delete");
        }finally{
            try{
                statement.close();
            }catch (SQLException ex){
                System.out.println("Gagal Update");
            }
        }
    }

    @Override
    public List<Kelas> getAll() {
        List<Kelas> lb = null;
        try{
            lb = new ArrayList<Kelas>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()){
                Kelas b = new Kelas();
                b.setId(rs.getInt("id"));
                b.setKelasId(rs.getString("kelasId"));
                b.setNamaKelas(rs.getString("namaKelas"));
                b.setNipWaliKelas(rs.getString("nipWaliKelas"));
                b.setTahunAjaran(rs.getInt("tahunAjaran"));
                b.setCapacity(rs.getInt("capacity"));
                lb.add(b);
            }
        }catch (SQLException ex){
            Logger.getLogger(DAOKelas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lb;
    }

    @Override
    public List<Kelas> getCariNamaKelas(String namaKelas) {
        List<Kelas> lb = null;
        try {
            lb =new ArrayList<Kelas>();
            PreparedStatement st = connection.prepareStatement(carinamakelas);
            st.setString(1, "%" + namaKelas  + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Kelas b = new Kelas();
                b.setId(rs.getInt("id"));
                b.setKelasId(rs.getString("kelasId"));
                b.setNamaKelas(rs.getString("namaKelas"));
                b.setNipWaliKelas(rs.getString("nipWaliKelas"));
                b.setTahunAjaran(rs.getInt("tahunAjaran"));
                b.setCapacity(rs.getInt("capacity"));
                lb.add(b);
            }
        } catch (SQLException ex){
            Logger.getLogger(DAOKelas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<Guru> getAllGuru() {
        List<Guru> lb = null;
        try{
            lb = new ArrayList<Guru>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(selectguru);
            while (rs.next()) {
                Guru b = new Guru();
                b.setId(rs.getInt("id"));
                b.setNip(rs.getString("nip"));
                b.setNama(rs.getString("nama"));
                lb.add(b);
            }
        }catch (SQLException ex){
            Logger.getLogger(DAOGuru.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lb;
    }
    
}
