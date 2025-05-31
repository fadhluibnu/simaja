/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Controller;
import mvc.DAO.DAOGuru;
import mvc.DAOInterface.IGuru;
import mvc.Model.Guru;
import mvc.Model.TabelModelGuru;
import mvc.View.FormGuru;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author NBN0C
 */
public class ControllerGuru {
    FormGuru frame;
    IGuru implGuru;
    List<Guru>lguru;
    
    public ControllerGuru(FormGuru frame){
    this.frame = frame;
    implGuru = new DAOGuru();
    lguru = implGuru.getAll();
}
    
   public void isiTable(){
    lguru = implGuru.getAll();
    TabelModelGuru tmb = new TabelModelGuru(lguru);
    frame.getTblGuru().setModel(tmb);
}
   
}

