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
/**
 *
 * @author HP
 */
public class ControllerJadwal {
    FormJadwal frame;
    IJadwal impIJadwal;
    List<Jadwal> ljadwal;
    List<Guru> lguru;
    List<MataPelajaran> lmatapelajaran;
    List<Kelas> lkelas;
    
    public ControllerJadwal (FormJadwal frame){
        this.frame = frame;
        impIJadwal = new DAOJadwal();
        ljadwal = impIJadwal.getAll();
    }
           
    public void isiTabel(){
    ljadwal = impIJadwal.getAll();
    TabelModelJadwal tmb = new TabelModelJadwal(ljadwal);
    frame.getDsnTabelJadwal().setModel(tmb);
    }
    
    public void isiTabelCariNama(){
    ljadwal = impIJadwal.getCariJadwal(frame.getCarijadwal().getSelectedItem().toString());
    TabelModelJadwal tmb = new TabelModelJadwal(ljadwal);
    frame.getDsnTabelJadwal().setModel(tmb);
    }
    
    public void carinama(){
        if (!frame.getCarijadwal().getSelectedItem().toString().trim().isEmpty()){
            impIJadwal.getCariJadwal(frame.getCarijadwal().getSelectedItem().toString());
            isiTabelCariNama();
        }else {
            JOptionPane.showMessageDialog(frame, "Silahkan Pilih Data");
        }
    }
    public void reset(){
        frame.getJadwalId().setText("");        
        frame.getHari().setSelectedItem("");
        frame.getJamMulai().setText("");
        frame.getJamSelesai().setText("");
        frame.getKelasId().setSelectedItem("");
        frame.getNipGuru().setSelectedItem("");
        frame.getKodeMapel().setSelectedItem("");

    }
    public void isiField(int row){
        frame.getJadwalId().setText(ljadwal.get(row).getJadwalId().toString());
        frame.getHari().setSelectedItem(ljadwal.get(row).getHari());
        frame.getJamMulai().setText(ljadwal.get(row).getJamMulai());
        frame.getJamSelesai().setText(ljadwal.get(row).getJamSelesai());
        frame.getKelasId().setSelectedItem(lkelas.get(row).getKelasId());
        frame.getNipGuru().setSelectedItem(lguru.get(row).getNip());
        frame.getKodeMapel().setSelectedItem(lmatapelajaran.get(row).getKodeMapel());
    }
    public void insert(){
    if (!frame.getJadwalId().getText().trim().isEmpty()&&!frame.getJadwalId().getText().trim().isEmpty()){
        
        Jadwal j = new Jadwal();
        j.setJadwalId(frame.getJadwalId().getText());
        j.setHari(frame.getHari().getSelectedItem().toString());
        j.setJamMulai(frame.getJamMulai().getText());
        j.setJamSelesai(frame.getJamSelesai().getText());
        j.setKelasId(frame.getKelasId().getSelectedItem().toString());
        j.setNipGuru(frame.getNipGuru().getSelectedItem().toString());
        j.setKodeMapel(frame.getKodeMapel().getSelectedItem().toString());
        
        impIJadwal.insert(j);
        JOptionPane.showMessageDialog(null, "Simpan Data Sukses");
    } else {
        JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }
    
    public void update(){
        if (!frame.getJadwalId().getText().trim().isEmpty()&&!frame.getJadwalId().getText().trim().isEmpty()){
        
        Jadwal j = new Jadwal();
        j.setJadwalId(frame.getJadwalId().getText());
        j.setHari(frame.getHari().getSelectedItem().toString());
        j.setJamMulai(frame.getJamMulai().getText());
        j.setJamSelesai(frame.getJamSelesai().getText());
        j.setKelasId(frame.getKelasId().getSelectedItem().toString());
        j.setNipGuru(frame.getNipGuru().getSelectedItem().toString());
        j.setKodeMapel(frame.getKodeMapel().getSelectedItem().toString());
        
        impIJadwal.insert(j);
    
            JOptionPane.showMessageDialog(null, "Update Data Sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih Data Yang Akan Dirubah");
        }
    }
    
    public void delete(){
        if (!frame.getJadwalId().getText().trim().isEmpty()){
            int id = Integer.parseInt(frame.getJadwalId().getText());
            impIJadwal.delete(id);
        JOptionPane.showMessageDialog(null, "Hapus Data Sukses");
        } else {
        JOptionPane.showMessageDialog(frame, "Pilih Data Yang Akan Dihapus");
        }
    }
}
