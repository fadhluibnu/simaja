/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAO;


import mvc.Koneksi.Koneksi;
import mvc.Model.Guru;
import mvc.DAOInterface.IGuru;
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
 * @author NBN0C
 */
public class DAOGuru implements IGuru {
    Connection connection;
    final String insert = "INSERT INTO Guru(nip,username,password,nama,alamat,noTelp,email,isActive) Values (?,?,?,?,?,?,?,?)";
    final String updte = " UPDATE Guru set nip=?, username=?, password=?, nama=?, alamat=?, noTelp=?, email=?, isActive=? where id=? ;"; // kalau terakhir ga pakai koma
    final String delate = "DELETE FROM Guru where id=? ;"; // jika mau dihapus semua tinggal hapus where nya 
    final String select = "SELECT * FROM Guru";
    final String carinamaguru = "SELECT * FROM Guru where nama like ?"; //tinggal ganti kalau seumpama nama ya nama kalau username ya username
    
    public DAOGuru (){
        connection = Koneksi.connection();
    }
    
    public void insert(Guru b) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getNip());
            statement.setString(2, b.getUsername());
            statement.setString(3, b.getPassword());
            statement.setString(4, b.getNama());
            statement.setString(5, b.getAlamat());
            statement.setString(6, b.getNoTelp());
            statement.setString(7, b.getEmail());
            statement.setString(8, b.getIsActive());
            statement.executeUpdate();                       
        }catch (SQLException ex){
//            System.out.println("berhasil input");
            System.out.println(ex.getMessage());

        } finally {
            try{
                statement.close();
            } catch (SQLException ex){
                System.out.println("Gagal Input");
            }
        }
    }
    
    public void update(Guru b){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(updte);
            statement.setString(1, b.getNip());
            statement.setString(2, b.getUsername());
            statement.setString(3, b.getPassword());
            statement.setString(4, b.getNama());
            statement.setString(5, b.getAlamat());
            statement.setString(6, b.getNoTelp());
            statement.setString(7, b.getEmail());
            statement.setString(8, b.getIsActive());
            statement.setInt(9, b.getId());// id harus di bawah
            
            statement.executeUpdate();
            
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        } finally{
            try{
                statement.close();
            }catch (SQLException ex){
                System.out.println("berhasil input"); 
            }
        } 
    }  
    
    public void delete(int id) {
    PreparedStatement statement = null;
    try {
        statement = connection.prepareStatement(delate);
        statement.setInt(1, id);  //untuk hapus semua di ilangin ini
        statement.executeUpdate();
        System.out.println("Berhasil Delete");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } finally {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
     public List<Guru> getAll(){
        List<Guru> lb = null;
        try{
            lb = new ArrayList<Guru>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Guru b = new Guru();
                b.setId(rs.getInt("id"));
                b.setNip(rs.getString("nip"));
                b.setUsername(rs.getString("username"));
                b.setPassword(rs.getString("password"));
                b.setNama(rs.getString("nama"));
                b.setAlamat(rs.getString("alamat"));
                b.setNoTelp(rs.getString("noTelp"));
                b.setEmail(rs.getString("email"));
                b.setIsActive(rs.getString("isActive"));
                lb.add(b);
            }
        }catch (SQLException ex){
            Logger.getLogger(DAOGuru.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lb;
    }

    @Override
    public List<Guru> getCariNama(String nama) {
        List<Guru> lb = null;
        try{
            lb = new ArrayList<Guru>();
            PreparedStatement st = connection.prepareStatement(carinamaguru);
            st.setString (1, "%" + nama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Guru b = new Guru ();
                b.setId(rs.getInt("id"));
                b.setNip(rs.getString("nip"));
                b.setUsername(rs.getString("username"));
                b.setPassword(rs.getString("password"));
                b.setNama(rs.getString("nama"));
                b.setAlamat(rs.getString("alamat"));
                b.setNoTelp(rs.getString("noTelp"));
                b.setEmail(rs.getString("email"));
                b.setIsActive(rs.getString("isActive"));

                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOGuru.class.getName()).log(Level.SEVERE,null,ex);
        }
        return lb;
    }
    
}
