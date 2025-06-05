/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAO;
import mvc.Koneksi.Koneksi;
import mvc.DAOInterface.IMataPelajaran;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mvc.Model.MataPelajaran;
import mvc.Model.Guru;

/**
 *
 * @author fibnu
 */
public class DAOMataPelajaran implements IMataPelajaran{
   Connection connection;
   final String insert = "INSERT INTO matapelajaran (id, kodeMapel, namaMapel, kkm, deskripsi, nipPengajar) VALUES (NULL, ?, ?, ?, ?, ?);";
   final String update = "UPDATE matapelajaran set kodeMapel=?, namaMapel=?, kkm=?, deskripsi=?, nipPengajar=? WHERE id=?";
   final String delete = "DELETE FROM matapelajaran where id=?;";
   final String selectmatapelajaran = "SELECT matapelajaran.* , guru.nip, guru.nama AS namaGuru FROM matapelajaran LEFT JOIN guru ON matapelajaran.nipPengajar = guru.nip";
   final String carimatapelajaran = "SELECT matapelajaran.* , guru.nip, guru.nama AS namaGuru FROM matapelajaran LEFT JOIN guru ON matapelajaran.nipPengajar = guru.nip WHERE matapelajaran.namaMapel LIKE ?";
   final String selectguru = "SELECT * FROM guru";
   
   public DAOMataPelajaran()
   {
       connection = Koneksi.connection();
   }
   
   public void insert(MataPelajaran mp, JFrame frame)
   {
       PreparedStatement statement = null;
       try
       {
           statement = connection.prepareStatement(insert);
           statement.setString(1, mp.getKodeMapel());
           statement.setString(2, mp.getNamaMapel());
           statement.setInt(3, mp.getKkm());
           statement.setString(4, mp.getDeskripsi());
           statement.setString(5, mp.getNipPengajar());
           statement.executeUpdate();
           
           JOptionPane.showMessageDialog(frame, "Simpan Berhasil");
       }catch(SQLException ex)
       {
           System.out.println(ex.getMessage());
           
           JOptionPane.showMessageDialog(frame, "Simpan Gagal");
       } finally 
       {
           try
           {
               statement.close();
           }catch(SQLException ex)
           {
               System.out.println("Gagal Input");
           }
       }
   }
   
   public void update(MataPelajaran mp)
   {
       PreparedStatement statement = null;
       try
       {
           statement = connection.prepareStatement(update);
           statement.setString(1, mp.getKodeMapel());
           statement.setString(2, mp.getNamaMapel());
           statement.setInt(3, mp.getKkm());
           statement.setString(4, mp.getDeskripsi());
           statement.setString(5, mp.getNipPengajar());
           statement.setInt(6, mp.getId());
           statement.executeUpdate();
       }catch (SQLException e)
       {
           System.out.println("Gagal Update");
       }finally
       {
           try {
               statement.close();
               System.out.println("Berhasil Update");
           } catch (SQLException ex)
           {
               System.out.println("Gagal Input");
           }
       }
   }
   
   public void delete (int id)
   {
       PreparedStatement statement = null;
       try
       {
           statement = connection.prepareStatement(delete);
           statement.setInt(1, id);
           statement.executeUpdate();
       }catch (SQLException ex)
       {
           System.out.println("Gagal Delete");
       }finally 
       {
           try
           {
               statement.close();
               System.out.println("Berhasil Delete");
           }catch (SQLException ex)
           {
               System.out.println("Gagal Update");
           }
       }
   }
   
   public List<MataPelajaran> getAll()
   {
       List<MataPelajaran> lb = null;
       try
       {
           lb = new ArrayList<MataPelajaran>();
           Statement st = connection.createStatement();
           ResultSet rs = st.executeQuery(selectmatapelajaran);
           
           while(rs.next())
           {
               MataPelajaran b = new MataPelajaran();
               b.setId(rs.getInt("id"));
               b.setKodeMapel(rs.getString("kodeMapel"));
               b.setNamaMapel(rs.getString("namaMapel"));
               b.setKkm(rs.getInt("kkm"));
               b.setNipPengajar(rs.getString("nipPengajar"));
               b.setNamaGuru(rs.getString("namaGuru"));
               b.setDeskripsi(rs.getString("deskripsi"));
               lb.add(b);
           }
           
       }catch(SQLException ex)
       {
           Logger.getLogger(DAOMataPelajaran.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return lb;
   }
   
   public List<MataPelajaran> getCariNama(String nama)
   {
       List<MataPelajaran> lb = null;
       try
       {
           lb = new ArrayList<MataPelajaran>();
           PreparedStatement st = connection.prepareStatement(carimatapelajaran);
           st.setString(1, "%" + nama + "%");
           ResultSet rs = st.executeQuery();
           while(rs.next())
           {
               MataPelajaran b = new MataPelajaran();
               b.setId(rs.getInt("id"));
               b.setKodeMapel(rs.getString("kodeMapel"));
               b.setNamaMapel(rs.getString("namaMapel"));
               b.setKkm(rs.getInt("kkm"));
               b.setNipPengajar(rs.getString("nipPengajar"));
               b.setNamaGuru(rs.getString("namaGuru"));
               b.setDeskripsi(rs.getString("deskripsi"));
               lb.add(b);
           }
       }catch(SQLException ex)
       {
           Logger.getLogger(DAOMataPelajaran.class.getName()).log(Level.SEVERE, null, ex);
           
       }
       
       return lb;
   }
}
