/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Controller;

import mvc.DAO.DAOMataPelajaran;
import mvc.DAOInterface.IMataPelajaran;
import mvc.Model.MataPelajaran;
import mvc.Model.TabelModelMataPelajaran;
import mvc.View.FormMataPelajaran;
import java.util.List;
import javax.swing.JOptionPane;
import mvc.Model.Guru;

/**
 *
 * @author fibnu
 */
public class ControllerMataPelajaran {
    
    FormMataPelajaran frame;
    IMataPelajaran implMataPelajaran;
    List<MataPelajaran> lmatapelajaran;
    List<Guru> lguru;
    
    public ControllerMataPelajaran (FormMataPelajaran frame)
    {
        this.frame = frame;
        implMataPelajaran = new DAOMataPelajaran();
        lmatapelajaran = implMataPelajaran.getAll();
    }

    public void isiTable()
    {
        lmatapelajaran = lmatapelajaran.getAll();
        TabelModelMataPelajaran tmb = new TabelModelMataPelajaran(lmatapelajaran);
        frame.getTabelData().setModel(tmb);
    }
}
