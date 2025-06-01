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
import mvc.DAO.DAOGuru;

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
    private ArrayList<String> guruNipList = new ArrayList<>();
    
    public ControllerMataPelajaran (FormMataPelajaran frame)
    {
        this.frame = frame;
        implMataPelajaran = new DAOMataPelajaran();
        lmatapelajaran = implMataPelajaran.getAll();
        
        implGuru = new DAOGuru();
        lguru = implGuru.getAll();
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

    public String getSelectedGuruNIP(JComboBox<String> comboBox) {
        int selectedIndex = comboBox.getSelectedIndex();

        if (selectedIndex <= 0) {
            return null; // Tidak ada guru yang dipilih atau placeholder dipilih
        }

        // Dapatkan NIP guru berdasarkan indeks yang dipilih
        // Perlu dikurangi 1 karena indeks 0 adalah placeholder
        int arrayIndex = selectedIndex - 1;

        if (arrayIndex >= 0 && arrayIndex < guruNipList.size()) {
            return guruNipList.get(arrayIndex);
        } else {
            return null;
        }
    }

    public void isiComboGuru(JComboBox<String> comboBox) {
        // Ambil data guru
        lguru = implGuru.getAll();

        // Reset array
        guruNipList.clear();

        // Kosongkan combo box terlebih dahulu
        comboBox.removeAllItems();

        // Tambahkan item placeholder
        comboBox.addItem("-- Pilih Guru --");

        // Tambahkan setiap guru dengan format yang diinginkan
        for (Guru guru : lguru) {
            // Tambahkan ke ComboBox
            comboBox.addItem(formatGuruForComboBox(guru));

            // Simpan NIP guru ke array
            guruNipList.add(guru.getNip());
        }
    }
    
    public void insert(String nipPengajar)
    {
        if(
                !frame.getKodeMapel().getText().trim().isEmpty() & 
                !frame.getNamaMapel().getText().trim().isEmpty() & 
                !frame.getKkm().getText().trim().isEmpty() & 
                frame.getNipPengajar().getSelectedIndex() != 0 & 
                !frame.getDeskripsi().getText().trim().isEmpty() 
          )
        {
            MataPelajaran b = new MataPelajaran();
            b.setKodeMapel(frame.getKodeMapel().getText());
            b.setNamaMapel(frame.getNamaMapel().getText());
            b.setKkm(Integer.valueOf(frame.getKkm().getText()));
            b.setNipPengajar(nipPengajar);
            b.setDeskripsi(frame.getDeskripsi().getText());
            implMataPelajaran.insert(b, frame);
        } else
        {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }
    
    public void reset()
    {
        frame.getKodeMapel().setText("");
        frame.getNamaMapel().setText("");
        frame.getKkm().setText("");
        frame.getNipPengajar().setSelectedIndex(0);
        frame.getDeskripsi().setText("");
    }
}
