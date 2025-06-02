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
}
