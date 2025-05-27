/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAO;
import mvc.Koneksi.Koneksi;
import mvc.Model.Jadwal;
import mvc.DAOInterface.IJadwal;
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
 * @author HP
 */
public class DAOJadwal implements IJadwal{
    Connection connection;
    final String insert = "INSERT INTO jadwal (jadwalId, hari, jamMulai, jamSelesai, kelasId, nipGuru, kodeMapel) VALUES (?, ?, ?, ?, ?, ?, ?);";
    final String update = "UPDATE jadwal set jadwalId=?, hari=?, jamMulai=?, jamSelesai=?, kelasId=?, nipGuru=?, kodeMapel=?, where id=? ;";
    final String delete = "DELETE FROM jadwal where id=? ;";
    final String select = "SELECT * FROM jadwal;";
    final String carijadwal = "SELECT * FROM jadwal where hari like ?";
    
    public DAOJadwal(){
        connection = Koneksi.connection();
    }

    @Override
    public void insert(Jadwal j) {
         PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, j.getHari());
            statement.setString(2, j.getJamMulai());
            statement.setString(3, j.getJamSelesai());
            statement.setString(4, j.getKelasId());
            statement.setString(5, j.getKodeMapel());
            statement.setString(6, j.getNipGuru());
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()){
                j.setJadwalId(rs.getString(1));
            }
        } catch (SQLException ex){
            System.out.println("Berhasil Input YEY!!!");
        } finally {
            try {
                statement.close();
            } catch (SQLException ex){
                System.out.println("Gagal Input Nih...");
            }
        }
    }

    @Override
    public void update(Jadwal j) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, j.getHari());
            statement.setString(2, j.getJamMulai());
            statement.setString(3, j.getJamSelesai());
            statement.setString(4, j.getKelasId());
            statement.setString(5, j.getKodeMapel());
            statement.setString(6, j.getNipGuru());
            statement.setInt(8, j.getId());
            statement.executeUpdate(); 
            
        } catch (SQLException ex){
            System.out.println("Berhasil Update Yeyy");
        } finally {
            try {
                statement.close();
            } catch (SQLException ex){
                System.out.println("Yahh Gagal Update..");
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
            
        } catch (SQLException ex){
            System.out.println("Berhasil Delete Yeyy..");
        } finally {
            try {
                statement.close();
            } catch (SQLException ex){
                System.out.println("Gagal Update Yah..");
            }
        }
    }

    @Override
    public List<Jadwal> getAll() {
        List<Jadwal> lb = null;
        try {
            lb = new ArrayList<Jadwal>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()){
                Jadwal j = new Jadwal();
                j.setId(rs.getInt("id"));
                j.setJadwalId(rs.getString("jadwalId"));
                j.setJamMulai(rs.getString("jamMulai"));
                j.setJamSelesai(rs.getString("jamSelesai"));
                j.setKelasId(rs.getString("kelasId"));
                j.setKodeMapel(rs.getString("kodeMapel"));
                lb.add(j);
            }
        } catch (SQLException ex){
            Logger.getLogger(DAOJadwal.class.getName()).log(Level.SEVERE,null,ex);
        }
        return lb;
    }

    @Override
    public List<Jadwal> getCariJadwal(String hari) {
        List<Jadwal> lb = null;
        try {
            lb = new ArrayList<Jadwal>();
            PreparedStatement st = connection.prepareStatement(carijadwal);
            st.setString(1, "%" + hari + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Jadwal j = new Jadwal();
                j.setId(rs.getInt("id"));
                j.setJadwalId(rs.getString("jadwalId"));
                j.setJamMulai(rs.getString("jamMulai"));
                j.setJamSelesai(rs.getString("jamSelesai"));
                j.setKelasId(rs.getString("kelasId"));
                j.setKodeMapel(rs.getString("kodeMapel"));
                lb.add(j);
            }
        } catch (SQLException ex){
            Logger.getLogger(DAOJadwal.class.getName()).log(Level.SEVERE,null,ex);
        }
        return lb;
    }
   
}
