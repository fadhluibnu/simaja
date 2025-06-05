/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Controller;

import mvc.DAO.DAOSiswa;
import mvc.DAOInterface.ISiswa;
import mvc.Model.Siswa;
import mvc.Model.TabelModelSiswa;
import mvc.Model.Kelas;
import mvc.View.FormSiswa;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.*;
import mvc.DAO.DAOKelas;
import mvc.DAOInterface.IKelas;

/**
 *
 * @author HP
 */
public class ControllerSiswa {
    FormSiswa frame;
    ISiswa impISiswa;
    IKelas impIKelas;
    List<Siswa> lSiswa;
    List<Kelas> lKelas;
    
    public ControllerSiswa(FormSiswa frame) {
        this.frame = frame;
        impISiswa = new DAOSiswa();
        lSiswa = impISiswa.getAll();
        
        impIKelas = new DAOKelas();
        lKelas = impIKelas.getAll();
    }
    
    public void reset() {
        frame.getTxtNIS().setText("");
        frame.getTxtUsername().setText("");
        frame.getTxtPassword().setText("");
        frame.getTxtNama().setText("");
        frame.getTxtTanggalLahir().setText("");
        frame.getTxtAlamat().setText("");
        frame.getTxtNoTelp().setText("");
        frame.getTxtEmail().setText("");
        frame.getCmbKelas().setSelectedIndex(0);
        frame.getCmbIsActive().setSelectedIndex(0);
    }
    
    public void isiTabel() {
        lSiswa = impISiswa.getAll();
        TabelModelSiswa tms = new TabelModelSiswa(lSiswa);
        frame.getTabelSiswa().setModel(tms);
    }
    
    public void isiField(int row) {
        frame.getTxtNIS().setText(lSiswa.get(row).getNis());
        frame.getTxtUsername().setText(lSiswa.get(row).getUsername());
        frame.getTxtPassword().setText(lSiswa.get(row).getPassword());
        frame.getTxtNama().setText(lSiswa.get(row).getNama());
        frame.getTxtTanggalLahir().setText(lSiswa.get(row).getTanggalLahir());
        frame.getTxtAlamat().setText(lSiswa.get(row).getAlamat());
        frame.getTxtNoTelp().setText(lSiswa.get(row).getNoTelp());
        frame.getTxtEmail().setText(lSiswa.get(row).getEmail());
        
        // Set selected item in combobox by ID
        String kelasId = lSiswa.get(row).getKelasId();
        for (int i = 0; i < frame.getCmbKelas().getItemCount(); i++) {
            String item = frame.getCmbKelas().getItemAt(i);
            if (item.contains(kelasId)) {
                frame.getCmbKelas().setSelectedIndex(i);
                break;
            }
        }
        
        // Set isActive combobox
        String isActive = lSiswa.get(row).getIsActive();
        if (isActive.equals("Aktif")) {
            frame.getCmbIsActive().setSelectedIndex(1);
        } else {
            frame.getCmbIsActive().setSelectedIndex(2);
        }
    }
    
    public void insert() {
        if (!validateForm()) {
            return;
        }
        
        Siswa siswa = new Siswa();
        siswa.setNis(frame.getTxtNIS().getText());
        siswa.setUsername(frame.getTxtUsername().getText());
        siswa.setPassword(frame.getTxtPassword().getText());
        siswa.setNama(frame.getTxtNama().getText());
        siswa.setTanggalLahir(frame.getTxtTanggalLahir().getText());
        siswa.setAlamat(frame.getTxtAlamat().getText());
        siswa.setNoTelp(frame.getTxtNoTelp().getText());
        siswa.setEmail(frame.getTxtEmail().getText());
        siswa.setKelasId(getSelectedKelasId());
        siswa.setIsActive(frame.getCmbIsActive().getSelectedItem().toString());
        
        impISiswa.insert(siswa);
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        reset();
        isiTabel();
    }
    
    public void update() {
        if (!validateForm()) {
            return;
        }
        
        Siswa siswa = new Siswa();
        siswa.setNis(frame.getTxtNIS().getText());
        siswa.setUsername(frame.getTxtUsername().getText());
        siswa.setPassword(frame.getTxtPassword().getText());
        siswa.setNama(frame.getTxtNama().getText());
        siswa.setTanggalLahir(frame.getTxtTanggalLahir().getText());
        siswa.setAlamat(frame.getTxtAlamat().getText());
        siswa.setNoTelp(frame.getTxtNoTelp().getText());
        siswa.setEmail(frame.getTxtEmail().getText());
        siswa.setKelasId(getSelectedKelasId());
        siswa.setIsActive(frame.getCmbIsActive().getSelectedItem().toString());
        
        impISiswa.update(siswa);
        JOptionPane.showMessageDialog(null, "Data berhasil diupdate");
        reset();
        isiTabel();
    }
    
    public void delete() {
        if (frame.getTxtNIS().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan dihapus");
            return;
        }
        
        int option = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            impISiswa.delete(frame.getTxtNIS().getText());
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
            reset();
            isiTabel();
        }
    }
    
    public void cariSiswa() {
        if (!frame.getTxtCariSiswa().getText().trim().isEmpty()) {
            lSiswa = impISiswa.getCariSiswa(frame.getTxtCariSiswa().getText());
            TabelModelSiswa tms = new TabelModelSiswa(lSiswa);
            frame.getTabelSiswa().setModel(tms);
        } else {
            JOptionPane.showMessageDialog(null, "Masukkan nama siswa yang ingin dicari");
            isiTabel();
        }
    }
    
    public void isiComboKelas() {
        frame.getCmbKelas().removeAllItems();
        frame.getCmbKelas().addItem("-- Pilih Kelas --");
        for (Kelas kelas : lKelas) {
            frame.getCmbKelas().addItem(formatKelasForComboBox(kelas));
        }
    }
    
    public String formatKelasForComboBox(Kelas kelas) {
        return kelas.getKelasId() + " - " + kelas.getNamaKelas();
    }
    
    public String getSelectedKelasId() {
        String selectedItem = frame.getCmbKelas().getSelectedItem().toString();
        if (selectedItem.equals("-- Pilih Kelas --")) {
            return "";
        } else {
            return selectedItem.split(" - ")[0];
        }
    }
    
    private boolean validateForm() {
        if (frame.getTxtNIS().getText().trim().isEmpty() ||
            frame.getTxtUsername().getText().trim().isEmpty() ||
            frame.getTxtPassword().getText().trim().isEmpty() ||
            frame.getTxtNama().getText().trim().isEmpty() ||
            frame.getTxtTanggalLahir().getText().trim().isEmpty() ||
            frame.getTxtAlamat().getText().trim().isEmpty() ||
            frame.getCmbKelas().getSelectedIndex() == 0 ||
            frame.getCmbIsActive().getSelectedIndex() == 0) {
            
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!");
            return false;
        }
        return true;
    }
    
    /**
     * Authenticate siswa login
     * 
     * @param username Siswa username
     * @param password Siswa password
     * @return Siswa object if authentication successful, null otherwise
     */
    public Siswa login(String username, String password) {
        DAOSiswa dao = new DAOSiswa();
        return dao.login(username, password);
    }
}
