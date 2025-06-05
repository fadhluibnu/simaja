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
    Integer id;
    
    // Map untuk menyimpan referensi ID guru berdasarkan posisi di combobox (tidak lagi diperlukan)
    // private ArrayList<String> guruNipList = new ArrayList<>();
    
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

        // Ambil string yang diformat dari combo box
        String selectedItem = (String) comboBox.getSelectedItem();
        // Pisahkan NIP dari string format "NIP - Nama Guru"
        String[] parts = selectedItem.split(" - ");
        if (parts.length > 0) {
            return parts[0]; // Mengembalikan NIP saja
        }
        return null;
    }

    public void isiComboGuru(JComboBox<String> comboBox) {
        lguru = implGuru.getAll(); // Ambil data guru
        comboBox.removeAllItems(); // Kosongkan combo box terlebih dahulu
        comboBox.addItem("-- Pilih Guru --"); // Tambahkan item placeholder

        // Tambahkan setiap guru dengan format yang diinginkan
        for (Guru guru : lguru) {
            comboBox.addItem(formatGuruForComboBox(guru)); // Tambahkan ke ComboBox
        }
    }
    
    // Tambahkan method isiField untuk MataPelajaran
    public void isiField(int row) {
        // Ambil data MataPelajaran dari baris yang diklik
        MataPelajaran selectedMapel = lmatapelajaran.get(row);

        // Set nilai ke JTextField
        frame.getKodeMapel().setText(selectedMapel.getKodeMapel());
        frame.getNamaMapel().setText(selectedMapel.getNamaMapel());
        frame.getKkm().setText(String.valueOf(selectedMapel.getKkm()));
        frame.getDeskripsi().setText(selectedMapel.getDeskripsi());

        // Cari guru yang sesuai untuk ComboBox
        String nipPengajarDariTabel = selectedMapel.getNipPengajar();
        boolean found = false;
        for (Guru guru : lguru) {
            if (guru.getNip().equals(nipPengajarDariTabel)) {
                frame.getNipPengajar().setSelectedItem(formatGuruForComboBox(guru));
                found = true;
                break;
            }
        }
        // Jika tidak ditemukan, set ke placeholder
        if (!found) {
            frame.getNipPengajar().setSelectedIndex(0);
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
    
    // Tambahkan method update
    public void update(String nipPengajar) {
        if (!frame.getKodeMapel().getText().trim().isEmpty()
                && !frame.getNamaMapel().getText().trim().isEmpty()
                && !frame.getKkm().getText().trim().isEmpty()
                && frame.getNipPengajar().getSelectedIndex() != 0
                && !frame.getDeskripsi().getText().trim().isEmpty()) {

            MataPelajaran b = new MataPelajaran();
            b.setId(lmatapelajaran.get(frame.getTabelMataPelajaran().getSelectedRow()).getId()); // Mendapatkan ID dari baris yang dipilih
            b.setKodeMapel(frame.getKodeMapel().getText());
            b.setNamaMapel(frame.getNamaMapel().getText());
            b.setKkm(Integer.valueOf(frame.getKkm().getText()));
            b.setNipPengajar(nipPengajar);
            b.setDeskripsi(frame.getDeskripsi().getText());
            implMataPelajaran.update(b);
            JOptionPane.showMessageDialog(frame, "Data berhasil diubah");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan diubah");
        }
    }

    // Tambahkan method delete
    public void delete() {
        if (this.id != 0) {
            int idToDelete = this.id;
            implMataPelajaran.delete(idToDelete);
            JOptionPane.showMessageDialog(frame, "Data berhasil dihapus");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan dihapus");
        }
    }
    
    public void isiTableCariNama()
    {
        lmatapelajaran = implMataPelajaran.getCariNama(frame.getCarimatapelajaran().getText());
        TabelModelMataPelajaran tmb = new TabelModelMataPelajaran(lmatapelajaran);
        frame.getTabelMataPelajaran().setModel(tmb);
    }
    
    public void cariNama()
    {
        if(!frame.getCarimatapelajaran().getText().trim().isEmpty()){
        implGuru.getCariNama(frame.getCarimatapelajaran().getText());
        isiTableCariNama();
        }else{
            JOptionPane.showMessageDialog(frame, "SILAHKAN PILIH DATA");
            isiTable();
        }
    }
}

//////*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package mvc.Controller;
//
//import mvc.DAO.DAOMataPelajaran;
//import mvc.DAOInterface.IMataPelajaran;
//import mvc.DAOInterface.IGuru;
//import mvc.Model.MataPelajaran;
//import mvc.Model.TabelModelMataPelajaran;
//import mvc.View.FormMataPelajaran;
//import java.util.List;
//import javax.swing.JOptionPane;
//import mvc.Model.Guru;
//import javax.swing.*;
//import java.util.*;
//import mvc.DAO.DAOGuru;
//
///**
// *
// * @author fibnu
// */
//public class ControllerMataPelajaran {
//    
//    FormMataPelajaran frame;
//    IMataPelajaran implMataPelajaran;
//    IGuru implGuru;
//    List<MataPelajaran> lmatapelajaran;
//    List<Guru> lguru;
//    
//    // Map untuk menyimpan referensi ID guru berdasarkan posisi di combobox
//    private ArrayList<String> guruNipList = new ArrayList<>();
//    
//    public ControllerMataPelajaran (FormMataPelajaran frame)
//    {
//        this.frame = frame;
//        implMataPelajaran = new DAOMataPelajaran();
//        lmatapelajaran = implMataPelajaran.getAll();
//        
//        implGuru = new DAOGuru();
//        lguru = implGuru.getAll();
//    }
//
//    public void isiTable()
//    {
//        lmatapelajaran = implMataPelajaran.getAll();
//        TabelModelMataPelajaran tmb = new TabelModelMataPelajaran(lmatapelajaran);
//        frame.getTabelMataPelajaran().setModel(tmb);
//    }
//    
//    public String formatGuruForComboBox(Guru guru) {
//        return guru.getNip() + " - " + guru.getNama();
//    }
//
//    public String getSelectedGuruNIP(JComboBox<String> comboBox) {
//        int selectedIndex = comboBox.getSelectedIndex();
//
//        if (selectedIndex <= 0) {
//            return null; // Tidak ada guru yang dipilih atau placeholder dipilih
//        }
//
//        // Dapatkan NIP guru berdasarkan indeks yang dipilih
//        // Perlu dikurangi 1 karena indeks 0 adalah placeholder
//        int arrayIndex = selectedIndex - 1;
//
//        if (arrayIndex >= 0 && arrayIndex < guruNipList.size()) {
//            return guruNipList.get(arrayIndex);
//        } else {
//            return null;
//        }
//    }
//
//    public void isiComboGuru(JComboBox<String> comboBox) {
//        // Ambil data guru
//        lguru = implGuru.getAll();
//
//        // Reset array
//        guruNipList.clear();
//
//        // Kosongkan combo box terlebih dahulu
//        comboBox.removeAllItems();
//
//        // Tambahkan item placeholder
//        comboBox.addItem("-- Pilih Guru --");
//
//        // Tambahkan setiap guru dengan format yang diinginkan
//        for (Guru guru : lguru) {
//            // Tambahkan ke ComboBox
//            comboBox.addItem(formatGuruForComboBox(guru));
//
//            // Simpan NIP guru ke array
//            guruNipList.add(guru.getNip());
//        }
//    }
//    
//    public void insert(String nipPengajar)
//    {
//        if(
//                !frame.getKodeMapel().getText().trim().isEmpty() & 
//                !frame.getNamaMapel().getText().trim().isEmpty() & 
//                !frame.getKkm().getText().trim().isEmpty() & 
//                frame.getNipPengajar().getSelectedIndex() != 0 & 
//                !frame.getDeskripsi().getText().trim().isEmpty() 
//          )
//        {
//            MataPelajaran b = new MataPelajaran();
//            b.setKodeMapel(frame.getKodeMapel().getText());
//            b.setNamaMapel(frame.getNamaMapel().getText());
//            b.setKkm(Integer.valueOf(frame.getKkm().getText()));
//            b.setNipPengajar(nipPengajar);
//            b.setDeskripsi(frame.getDeskripsi().getText());
//            implMataPelajaran.insert(b, frame);
//        } else
//        {
//            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
//        }
//    }
//    
//    public void reset()
//    {
//        frame.getKodeMapel().setText("");
//        frame.getNamaMapel().setText("");
//        frame.getKkm().setText("");
//        frame.getNipPengajar().setSelectedIndex(0);
//        frame.getDeskripsi().setText("");
//    }
//}
