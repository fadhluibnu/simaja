/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Controller;
import mvc.DAO.DAOKelas;
import mvc.DAOInterface.IKelas;
import mvc.Model.Kelas;
import mvc.Model.TabelModelKelas;
import mvc.View.FormKelas;
import java.util.List;
import javax.swing.JOptionPane;
import mvc.Model.Guru;
/**
 *
 * @author nabig
 */
public class ControllerKelas {
    FormKelas frame;
    IKelas implKelas;
    List<Kelas> lkelas;
    List<Guru> lGuru;
    
    public ControllerKelas(FormKelas frame){
        this.frame = frame;
        implKelas = new DAOKelas();
        lkelas = implKelas.getAll();
    }
    
    //menampilkan data kedalam tabel
    public void isiTabel(){
        lkelas = implKelas.getAll();
        TabelModelKelas tmb = new TabelModelKelas(lkelas);
        frame.getjTable1().setModel(tmb);
    }
    
    public void isiTableCariNama(){
        lkelas = implKelas.getCariNamaKelas(frame.getCariNama().getText());
        TabelModelKelas tmb = new TabelModelKelas(lkelas);
        frame.getjTable1().setModel(tmb);
    }
    
    public void carinamakelas(){
        if (!frame.getCariNama().getText().trim().isEmpty()){
            implKelas.getCariNamaKelas(frame.getCariNama().getText());
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame, "Silahkan pilih data");
        }
    }
    
}
