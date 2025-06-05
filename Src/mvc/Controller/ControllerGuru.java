/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Controller;
import mvc.DAO.DAOGuru;
import mvc.DAOInterface.IGuru;
import mvc.Model.Guru;
import mvc.Model.TabelModelGuru;
import mvc.View.FormGuruNew;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author NBN0C
 */
public class ControllerGuru {
    FormGuruNew frame;
    IGuru implGuru;
    List<Guru>lguru;
    
    Integer id;
    
    public ControllerGuru(FormGuruNew frame)
    {
        this.frame = frame;
        implGuru = new DAOGuru();
        lguru = implGuru.getAll();
    }
    
    
   public void isiTable()
   {
        lguru = implGuru.getAll();
        TabelModelGuru tmb = new TabelModelGuru(lguru);
        frame.getTblGuruN().setModel(tmb);
   }
   
   public void isiField (int row)
   {
    this.id = lguru.get(row).getId();
    frame.getNipGuru().setText(lguru.get(row).getNip().toString());
    frame.getUserGuru().setText(lguru.get(row).getUsername());
    frame.getPassGuru().setText(lguru.get(row).getPassword());
    frame.getNmaGuru().setText(lguru.get(row).getNama());
    frame.getAlmtGuru().setText(lguru.get(row).getAlamat());
    frame.getEmailGuru().setText(lguru.get(row).getEmail());
    frame.getNotlpGuru().setText(lguru.get(row).getNoTelp());
    frame.getCmbGuru().setSelectedItem(lguru.get(row).getIsActive());

    }
    public void insert()
    {
        if (    
                !frame.getNipGuru().getText().trim().isEmpty()& 
                !frame.getUserGuru().getText().trim().isEmpty()&
                !frame.getNmaGuru().getText().trim().isEmpty()&
                !frame.getAlmtGuru().getText().trim().isEmpty()&
                !frame.getEmailGuru().getText().trim().isEmpty()&
                !frame.getNotlpGuru().getText().trim().isEmpty()& // jangan lupa pakai !
                frame.getCmbGuru().getSelectedIndex()!=0  
            )
        {    
            Guru b = new Guru();
            b.setNip(frame.getNipGuru().getText());
            b.setUsername(frame.getUserGuru().getText());
            b.setPassword(frame.getPassGuru().getText());
            b.setNama(frame.getNmaGuru().getText());
            b.setAlamat(frame.getAlmtGuru().getText());
            b.setEmail(frame.getEmailGuru().getText());
            b.setNoTelp(frame.getNotlpGuru().getText());
            b.setIsActive(frame.getCmbGuru().getSelectedItem().toString());
                         //pakai integer.presetint jika tidak frame 
            implGuru.insert(b);
        } else
        {
            JOptionPane.showMessageDialog(frame, "Data tidak Boleh Kosong");
        }
   }
    
    public void reset()
    {
        
        frame.getNipGuru().setText("");
        frame.getUserGuru().setText("");
        frame.getPassGuru().setText("");
        frame.getNmaGuru().setText("");
        frame.getAlmtGuru().setText("");
        frame.getEmailGuru().setText("");
        frame.getNotlpGuru().setText("");
        frame.getCmbGuru().setSelectedIndex(0);

    }
    
    public void updte()
    {
        if (    
                !frame.getNipGuru().getText().trim().isEmpty()& 
                !frame.getUserGuru().getText().trim().isEmpty()&
                !frame.getNmaGuru().getText().trim().isEmpty()&
                !frame.getAlmtGuru().getText().trim().isEmpty()&
                !frame.getEmailGuru().getText().trim().isEmpty()&
                !frame.getNotlpGuru().getText().trim().isEmpty()&
                frame.getCmbGuru().getSelectedIndex()!=0 // kalau di akhirr tidak pakai &
            )
        {    
            Guru b = new Guru();
            b.setNip(frame.getNipGuru().getText());
            b.setUsername(frame.getUserGuru().getText());
            b.setPassword(frame.getPassGuru().getText());
            b.setNama(frame.getNmaGuru().getText());
            b.setAlamat(frame.getAlmtGuru().getText());
            b.setEmail(frame.getEmailGuru().getText());
            b.setNoTelp(frame.getNotlpGuru().getText());
            b.setIsActive(frame.getCmbGuru().getSelectedItem().toString());
                 // jika tidak pakai frame 
            b.setId(this.id);
            implGuru.update(b);
        } else
        {
            JOptionPane.showMessageDialog(frame, "Data tidak Boleh Kosong");
        }
   }
    
    
    public void isiTableCariNama()
    {
        lguru = implGuru.getCariNama(frame.getCarinamaGuru().getText());
        TabelModelGuru tmb = new TabelModelGuru(lguru);
        frame.getTblGuruN().setModel(tmb);
    }

    public void CariNama()
    {
        if(!frame.getCarinamaGuru().getText().trim().isEmpty()){
        implGuru.getCariNama(frame.getCarinamaGuru().getText());
        isiTableCariNama();
        }else{
            JOptionPane.showMessageDialog(frame, "SILAHKAN PILIH DATA");
        }
    }
    
    public void delete()
    {
        if (this.id !=0){
        int id = this.id;
        implGuru.delete(id);
        JOptionPane.showMessageDialog(null, "Hapus Data sukses");
        }else{
            JOptionPane.showMessageDialog(frame, "Pilih Data yang akan dihapus");
        }
    }

    /**
     * Authenticate guru login
     * 
     * @param username Guru username
     * @param password Guru password
     * @return Guru object if authentication successful, null otherwise
     */
    public Guru login(String username, String password) {
        DAOGuru dao = new DAOGuru();
        return dao.login(username, password);
    }
}

