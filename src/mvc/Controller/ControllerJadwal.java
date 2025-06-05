/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Controller;

import mvc.DAO.DAOJadwal;
import mvc.DAOInterface.IJadwal;
import mvc.Model.Jadwal;
import mvc.Model.Guru;
import mvc.Model.MataPelajaran;
import mvc.Model.TabelModelJadwal;
import mvc.View.FormJadwal;
import java.util.List;
import javax.swing.JOptionPane;
import mvc.Model.Kelas;
import java.util.ArrayList;
import javax.swing.*;
import mvc.DAO.DAOGuru;
import mvc.DAO.DAOKelas;
import mvc.DAO.DAOMataPelajaran;
import mvc.DAOInterface.IGuru;
import mvc.DAOInterface.IKelas;
import mvc.DAOInterface.IMataPelajaran;

/**
 *
 * @author HP
 */

public class ControllerJadwal {

    FormJadwal frame;
    IJadwal impIJadwal;
    IGuru implGuru;
    IKelas implKelas;
    IMataPelajaran implMapel;
    List<Jadwal> ljadwal;
    List<Guru> lguru;
    List<MataPelajaran> lmatapelajaran;
    List<Kelas> lkelas;

    Integer id;

//    private ArrayList<String> guruNipList = new ArrayList<>();
//    private ArrayList<String> kelasList = new ArrayList<>();
//    private ArrayList<String> mapelList = new ArrayList<>();

    public ControllerJadwal(FormJadwal frame) {
        this.frame = frame;
        impIJadwal = new DAOJadwal();
        ljadwal = impIJadwal.getAll();

        implGuru = new DAOGuru();
        lguru = implGuru.getAll();

        implKelas = new DAOKelas();
        lkelas = implKelas.getAll();

        implMapel = new DAOMataPelajaran();
        lmatapelajaran = implMapel.getAll();
    }

    public void isiTabel() {
        ljadwal = impIJadwal.getAll();
        TabelModelJadwal tmb = new TabelModelJadwal(ljadwal);
        frame.getDsnTabelJadwal().setModel(tmb);
    }

    public void isiTabelCariNama() {
        ljadwal = impIJadwal.getCariJadwal(frame.getCarijadwal().getSelectedItem().toString());
        TabelModelJadwal tmb = new TabelModelJadwal(ljadwal);
        frame.getDsnTabelJadwal().setModel(tmb);
    }

    public void carinama() {
        if (!frame.getCarijadwal().getSelectedItem().toString().trim().isEmpty()) {
            impIJadwal.getCariJadwal(frame.getCarijadwal().getSelectedItem().toString());
            isiTabelCariNama();
        } else {
            JOptionPane.showMessageDialog(frame, "Silahkan Pilih Data");
        }
    }

    public void reset() {
        frame.getJadwalId().setText("");
        frame.getHari().setSelectedIndex(0);
        frame.getJamMulai().setText("");
        frame.getJamSelesai().setText("");
        frame.getKelasId().setSelectedIndex(0);
        frame.getNipGuru().setSelectedIndex(0);
        frame.getKodeMapel().setSelectedIndex(0);

    }

    public void isiField(int row) {
        this.id = ljadwal.get(row).getId();
        frame.getJadwalId().setText(ljadwal.get(row).getJadwalId().toString());
        frame.getHari().setSelectedItem(ljadwal.get(row).getHari());
        frame.getJamMulai().setText(ljadwal.get(row).getJamMulai());
        frame.getJamSelesai().setText(ljadwal.get(row).getJamSelesai());
        
        // --- SOLUSI MASALAH 1 & 2 DIMULAI DI SINI ---
        // Mencari dan mengatur Kelas
        String selectedKelasId = ljadwal.get(row).getKelasId();
        for (int i = 0; i < lkelas.size(); i++) {
            if (lkelas.get(i).getKelasId().equals(selectedKelasId)) {
                frame.getKelasId().setSelectedItem(formatKelasForComboBox(lkelas.get(i)));
                break;
            }
        }

        // Mencari dan mengatur Guru
        String selectedNipGuruFormatted = ljadwal.get(row).getNipGuru(); // Ini akan berisi "NIP - NamaGuru"
        String selectedNipGuru = selectedNipGuruFormatted.split(" - ")[0]; // Ambil hanya NIP nya
        for (int i = 0; i < lguru.size(); i++) {
            if (lguru.get(i).getNip().equals(selectedNipGuru)) {
                frame.getNipGuru().setSelectedItem(formatGuruForComboBox(lguru.get(i)));
                break;
            }
        }

        // Mencari dan mengatur Mata Pelajaran
        String selectedKodeMapel = ljadwal.get(row).getKodeMapel();
        for (int i = 0; i < lmatapelajaran.size(); i++) {
            if (lmatapelajaran.get(i).getKodeMapel().equals(selectedKodeMapel)) {
                frame.getKodeMapel().setSelectedItem(formatMatapelajaranForComboBox(lmatapelajaran.get(i)));
                break;
            }
        }
    }

    public void insert(String nipguru, String idkelas, String kodemapel) {
        if (!frame.getJadwalId().getText().trim().isEmpty()
                & frame.getHari().getSelectedIndex() != 0
                & !frame.getJamMulai().getText().trim().isEmpty()
                & !frame.getJamSelesai().getText().trim().isEmpty()
                & frame.getKelasId().getSelectedIndex() != 0
                & frame.getNipGuru().getSelectedIndex() != 0
                & frame.getKodeMapel().getSelectedIndex() != 0) {

            Jadwal j = new Jadwal();
            j.setJadwalId(frame.getJadwalId().getText());
            j.setHari(frame.getHari().getSelectedItem().toString());
            j.setJamMulai(frame.getJamMulai().getText());
            j.setJamSelesai(frame.getJamSelesai().getText());
            j.setKelasId(idkelas);
            j.setNipGuru(nipguru);
            j.setKodeMapel(kodemapel);

            impIJadwal.insert(j);
            JOptionPane.showMessageDialog(null, "Simpan Data Sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }

    public void update(String nipguru, String idkelas, String kodemapel) {
        if (!frame.getJadwalId().getText().trim().isEmpty()
                & frame.getHari().getSelectedIndex() != 0
                & !frame.getJamMulai().getText().trim().isEmpty()
                & !frame.getJamSelesai().getText().trim().isEmpty()
                & frame.getKelasId().getSelectedIndex() != 0
                & frame.getNipGuru().getSelectedIndex() != 0
                & frame.getKodeMapel().getSelectedIndex() != 0) {

            Jadwal j = new Jadwal();
            j.setId(this.id);
            j.setJadwalId(frame.getJadwalId().getText());
            j.setHari(frame.getHari().getSelectedItem().toString());
            j.setJamMulai(frame.getJamMulai().getText());
            j.setJamSelesai(frame.getJamSelesai().getText());
            j.setKelasId(idkelas);
            j.setNipGuru(nipguru);
            j.setKodeMapel(kodemapel);

            impIJadwal.update(j);

            JOptionPane.showMessageDialog(null, "Update Data Sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih Data Yang Akan Dirubah");
        }
    }

    public void delete() {
        if (this.id != 0) {
            int id = this.id;
            impIJadwal.delete(id);
            JOptionPane.showMessageDialog(null, "Hapus Data Sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih Data Yang Akan Dihapus");
        }
    }

    public String formatGuruForComboBox(Guru guru) {
        return guru.getNip() + " - " + guru.getNama(); // Menggunakan getNama() untuk nama guru
    }

    public String formatKelasForComboBox(Kelas kelas) {
        return kelas.getKelasId() + " - " + kelas.getNamaKelas();
    }

    public String formatMatapelajaranForComboBox(MataPelajaran mapel) {
        return mapel.getKodeMapel() + " - " + mapel.getNamaMapel();
    }

    public String getSelectedGuruNIP(JComboBox<String> comboBox) {
        int selectedIndex = comboBox.getSelectedIndex();

        if (selectedIndex <= 0) {
            return null; // Tidak ada guru yang dipilih atau placeholder dipilih
        }

        // Dapatkan NIP guru berdasarkan indeks yang dipilih
        // Perlu dikurangi 1 karena indeks 0 adalah placeholder
        String selectedItem = (String) comboBox.getSelectedItem();
        // Memisahkan NIP dari string format "NIP - Nama Guru"
        String[] parts = selectedItem.split(" - ");
        if (parts.length > 0) {
            return parts[0];
        }
        return null;
    }

    public String getSelectedIdKelas(JComboBox<String> comboBox) {
        int selectedIndex = comboBox.getSelectedIndex();

        if (selectedIndex <= 0) {
            return null; // Tidak ada guru yang dipilih atau placeholder dipilih
        }

        String selectedItem = (String) comboBox.getSelectedItem();
        String[] parts = selectedItem.split(" - ");
        if (parts.length > 0) {
            return parts[0];
        }
        return null;
    }

    public String getSelectedKodeMapel(JComboBox<String> comboBox) {
        int selectedIndex = comboBox.getSelectedIndex();

        if (selectedIndex <= 0) {
            return null; // Tidak ada guru yang dipilih atau placeholder dipilih
        }

        String selectedItem = (String) comboBox.getSelectedItem();
        String[] parts = selectedItem.split(" - ");
        if (parts.length > 0) {
            return parts[0];
        }
        return null;
    }

    public void isiComboGuru(JComboBox<String> comboBox) {
        lguru = implGuru.getAll();
        comboBox.removeAllItems();
        comboBox.addItem("-- Pilih Guru --");
        for (Guru guru : lguru) {
            comboBox.addItem(formatGuruForComboBox(guru));
        }
    }

    public void isiComboKelas(JComboBox<String> comboBox) {
        lkelas = implKelas.getAll();
        comboBox.removeAllItems();
        comboBox.addItem("-- Pilih Kelas --");
        for (Kelas kelas : lkelas) {
            comboBox.addItem(formatKelasForComboBox(kelas));
        }
    }

    public void isiComboMataPelajaran(JComboBox<String> comboBox) {
        lmatapelajaran = implMapel.getAll();
        comboBox.removeAllItems();
        comboBox.addItem("-- Pilih Mapel --");
        for (MataPelajaran mapel : lmatapelajaran) {
            comboBox.addItem(formatMatapelajaranForComboBox(mapel));
        }
    }
}