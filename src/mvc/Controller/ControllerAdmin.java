/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Controller;
import mvc.DAO.DAOAdmin;
import mvc.DAOInterface.IAdmin;
import mvc.Model.Admin;
import mvc.Model.TabelModelAdmin;
import mvc.View.FormAdmin;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gansp
 */
public class ControllerAdmin {
    FormAdmin frame;
    IAdmin implAdmin;
    List<Admin> lAdmin;
    
    Integer id;
    
    public ControllerAdmin (FormAdmin frame)
    {
        this.frame = frame;
        implAdmin = new DAOAdmin();
        lAdmin = implAdmin.getAll();
    }
    public void isiTable()
    {
        lAdmin = implAdmin.getAll();
        TabelModelAdmin tmb = new TabelModelAdmin(lAdmin);
        frame.getTableAdmin().setModel(tmb);
    }
    public void isiTableCariNama()
    {
        lAdmin = implAdmin.getCariNama(frame.getCarinama().getText());
        TabelModelAdmin tmb = new TabelModelAdmin(lAdmin);
        frame.getTableAdmin().setModel(tmb);
    }
    public void carinama()
    {
        if(!frame.getCarinama().getText().trim().isEmpty())
        {
            implAdmin.getCariNama(frame.getCarinama().getText());
            isiTableCariNama();
        }else
        {
            JOptionPane.showMessageDialog(null, "Silahkan Pilih Data");
        }
    }
    public void isiField(int row) {
    this.id = lAdmin.get(row).getId();
    frame.getAdminId().setText(lAdmin.get(row).getAdminId().toString());
    frame.getEmail().setText(lAdmin.get(row).getEmail());

    frame.getNama().setText(lAdmin.get(row).getNama());
    frame.getNoTelp().setText(lAdmin.get(row).getNoTelp());
    frame.getPassword().setText(lAdmin.get(row).getPassword());
    frame.getUsername().setText(lAdmin.get(row).getUsername());
    frame.getIsActive().setSelectedItem(lAdmin.get(row).getIsActive());
   
    frame.getRole().setSelectedItem(lAdmin.get(row).getRole());

    }
     public void insert(){
        if(
                !frame.getAdminId().getText().trim().isEmpty() &
                !frame.getEmail().getText().trim().isEmpty() & 
                
                !frame.getNama().getText().trim().isEmpty() & 
                !frame.getNoTelp().getText().trim().isEmpty() & 
                !frame.getPassword().getText().trim().isEmpty() & 
                !frame.getUsername().getText().trim().isEmpty() & 

                frame.getIsActive().getSelectedIndex() != 0 & 
                frame.getRole().getSelectedIndex() != 0 
          )
        {
            Admin b = new Admin();
            b.setAdminId(frame.getAdminId().getText());
            b.setEmail(frame.getEmail().getText());

            b.setNama(frame.getAdminId().getText());
            b.setNoTelp(frame.getEmail().getText());
            b.setPassword(frame.getAdminId().getText());
            b.setUsername(frame.getEmail().getText());
            b.setIsActive(frame.getIsActive().getSelectedItem().toString());
            b.setRole(frame.getRole().getSelectedItem().toString());
            
          implAdmin.insert(b);
            
        } else
        {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }
        public void reset()
    {
        frame.getAdminId().setText("");
        frame.getEmail().setText("");

        frame.getNama().setText("");
        frame.getNoTelp().setText("");
        frame.getPassword().setText("");
        frame.getUsername().setText("");
        frame.getIsActive().setSelectedIndex(0);
        frame.getRole().setSelectedIndex(0);

    }
    public void update(){
        if(
                !frame.getAdminId().getText().trim().isEmpty() &
                !frame.getEmail().getText().trim().isEmpty() & 
                
                !frame.getNama().getText().trim().isEmpty() & 
                !frame.getNoTelp().getText().trim().isEmpty() & 
                !frame.getPassword().getText().trim().isEmpty() & 
                !frame.getUsername().getText().trim().isEmpty() & 
                frame.getIsActive().getSelectedIndex() != 0 & 
                frame.getRole().getSelectedIndex() != 0 
          )
        {
            Admin b = new Admin();
            b.setAdminId(frame.getAdminId().getText());
            b.setEmail(frame.getEmail().getText());
    
            b.setNama(frame.getAdminId().getText());
            b.setNoTelp(frame.getEmail().getText());
            b.setPassword(frame.getAdminId().getText());
            b.setUsername(frame.getEmail().getText());
            b.setIsActive(frame.getIsActive().getSelectedItem().toString());
            b.setRole(frame.getRole().getSelectedItem().toString());
            b.setId(this.id);
            
          implAdmin.update(b);
            
        } else
        {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }
    public void delete()
    {
        if(this.id != 0)
        {
            int id = this.id;
            implAdmin.delete(id);
            JOptionPane.showMessageDialog(null, "Hapus Data Sukses");
        }else
        {
            JOptionPane.showMessageDialog(null, "Pilih Data Yang Akan Dihapus");
        }
    }
    
    
}
