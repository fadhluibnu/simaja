/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Controller;

import mvc.DAO.DAOMataPelajaran;
import mvc.DAOInterface.IMataPelajaran;
import mvc.DAOInterface.IGuru;
import mvc.Model.MataPelajaran;
import mvc.Model.TabelModelMataPelajaran;
import mvc.View.FormMataPelajaran;
import java.util.List;
import javax.swing.JOptionPane;
import mvc.Model.Guru;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author fibnu
 */
public class ControllerMataPelajaran {
    
    FormMataPelajaran frame;
    IMataPelajaran implMataPelajaran;
    IGuru implGuru;
    List<MataPelajaran> lmatapelajaran;
    List<Guru> lguru;
    
    // Map untuk menyimpan referensi ID guru berdasarkan posisi di combobox
    private Map<Integer, Integer> comboIndexToGuruId = new HashMap<>();
    
    public ControllerMataPelajaran (FormMataPelajaran frame)
    {
        this.frame = frame;
        implMataPelajaran = new DAOMataPelajaran();
        lmatapelajaran = implMataPelajaran.getAll();
    }

    public void isiTable()
    {
        lmatapelajaran = implMataPelajaran.getAll();
        TabelModelMataPelajaran tmb = new TabelModelMataPelajaran(lmatapelajaran);
        frame.getTabelMataPelajaran().setModel(tmb);
    }
    
    public String formatGuruForComboBox(Guru guru) {
        return guru.getNip() + " - " + guru.getNama();
    }
    
    public void isiComboGuru(JComboBox<String> comboBox) {
        lguru = implGuru.getAll();
        
        // Reset map
        comboIndexToGuruId.clear();
        
        // Kosongkan combo box terlebih dahulu
        comboBox.removeAllItems();
        
        // Tambahkan item placeholder
        comboBox.addItem("-- Pilih Guru --");
        
        // Tambahkan setiap guru dengan format yang diinginkan
        int index = 1; // Mulai dari 1 karena 0 adalah placeholder
        for (Guru guru : lguru) {
            comboBox.addItem(formatGuruForComboBox(guru));
            comboIndexToGuruId.put(index, guru.getId());
            index++;
        }
    }
}
