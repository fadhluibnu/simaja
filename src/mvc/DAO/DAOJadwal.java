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
import mvc.Model.Guru;
import mvc.Model.MataPelajaran;
/**
 *
 * @author HP
 */
public class DAOJadwal implements IJadwal{
    Connection connection;
    final String insert = "INSERT INTO jadwal (jadwalId, hari, jamMulai, jamSelesai, kelasId, nipGuru, kodeMapel) VALUES (?, ?, ?, ?, ?, ?, ?);";
    final String update = "UPDATE jadwal set jadwalId=?, hari=?, jamMulai=?, jamSelesai=?, kelasId=?, nipGuru=?, kodeMapel=? where id=? ;";
    final String delete = "DELETE FROM jadwal where id=? ;";
    final String select = "SELECT jadwal.*,guru.nama AS namaguru FROM jadwal INNER JOIN guru ON jadwal.nipGuru = guru.nip;";
    final String carijadwal = "SELECT * FROM jadwal WHERE hari like ?";
    final String selectguru = "SELECT * FROM guru";
    final String selectmatapelajaran = "SELECT * FROM matapelajaran";
    
    public DAOJadwal(){
        connection = Koneksi.connection();
    }

    @Override
    public void insert(Jadwal j) {
         PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, j.getJadwalId());
            statement.setString(2, j.getHari());
            statement.setString(3, j.getJamMulai());
            statement.setString(4, j.getJamSelesai());
            statement.setString(5, j.getKelasId());
            statement.setString(7, j.getKodeMapel());
            statement.setString(6, j.getNipGuru());
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
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
            statement.setString(1, j.getJadwalId());
            statement.setString(2, j.getHari());
            statement.setString(3, j.getJamMulai());
            statement.setString(4, j.getJamSelesai());
            statement.setString(5, j.getKelasId());
            statement.setString(6, j.getNipGuru());
            statement.setString(7, j.getKodeMapel());
            statement.setInt(8, j.getId());
            statement.executeUpdate(); 
            
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
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
                j.setHari(rs.getString("hari"));
                j.setJamMulai(rs.getString("jamMulai"));
                j.setJamSelesai(rs.getString("jamSelesai"));
                j.setKelasId(rs.getString("kelasId"));
                j.setNipGuru(rs.getString("nipGuru")+ " - " + rs.getString("namaguru"));
                j.setKodeMapel(rs.getString("kodeMapel"));
//                j.setNamaGuru(rs.getString("namaguru"));
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
                j.setHari(rs.getString("hari"));
                j.setJamMulai(rs.getString("jamMulai"));
                j.setJamSelesai(rs.getString("jamSelesai"));
                j.setKelasId(rs.getString("kelasId"));
                j.setNipGuru(rs.getString("nipGuru"));
                j.setKodeMapel(rs.getString("kodeMapel"));
                lb.add(j);
            }
        } catch (SQLException ex){
            Logger.getLogger(DAOJadwal.class.getName()).log(Level.SEVERE,null,ex);
        }
        return lb;
    }
}
