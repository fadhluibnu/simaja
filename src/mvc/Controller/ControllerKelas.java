/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Controller;
import mvc.DAO.DAOKelas;
import mvc.DAOInterface.IKelas;
import mvc.Model.Kelas;
import mvc.Model.TabelModelKelas;
import mvc.View.FormKelas;
import java.util.List;
import javax.swing.JOptionPane;
import mvc.Model.Guru;
import java.util.ArrayList;
import javax.swing.JComboBox;
import mvc.DAO.DAOGuru;
import mvc.DAOInterface.IGuru;
/**
 *
 * @author nabig
 */
public class ControllerKelas {
    FormKelas frame;
    IKelas implKelas;
    List<Kelas> lkelas;
    List<Guru> lGuru;
    IGuru implGuru;
    
    Integer id;
    
    private ArrayList<String> walikelasList = new ArrayList<>();
    
    public ControllerKelas(FormKelas frame){
        this.frame = frame;
        implKelas = new DAOKelas();
        lkelas = implKelas.getAll();
        
        implGuru = new DAOGuru();
        lGuru = implGuru.getAll();
    }
    
    //menampilkan data kedalam tabel
    public void isiTabel(){
        lkelas = implKelas.getAll();
        TabelModelKelas tmb = new TabelModelKelas(lkelas);
        frame.getjTable1().setModel(tmb);
    }
    
    public void isiTableCariNama(){
        lkelas = implKelas.getCariNamaKelas(frame.getCariNama().getText());
        TabelModelKelas tmb = new TabelModelKelas(lkelas);
        frame.getjTable1().setModel(tmb);
    }
    
    public void carinamakelas(){
        if (!frame.getCariNama().getText().trim().isEmpty()){
            implKelas.getCariNamaKelas(frame.getCariNama().getText());
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame, "Silahkan pilih data");
        }
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

        if (arrayIndex >= 0 && arrayIndex < walikelasList.size()) {
            return walikelasList.get(arrayIndex);
        } else {
            return null;
        }
    }
    
    public void isiComboGuru(JComboBox<String> comboBox) {
        // Ambil data guru
        lGuru = implGuru.getAll();

        // Reset array
        walikelasList.clear();

        // Kosongkan combo box terlebih dahulu
        comboBox.removeAllItems();

        // Tambahkan item placeholder
        comboBox.addItem("-- Pilih Guru --");

        // Tambahkan setiap guru dengan format yang diinginkan
        for (Guru guru : lGuru) {
            // Tambahkan ke ComboBox
            comboBox.addItem(formatGuruForComboBox(guru));

            // Simpan NIP guru ke array
            walikelasList.add(guru.getNip());
        }
    }
    
    public void isiField(int row){
        this.id = lkelas.get(row).getId();
        frame.getKodekelas().setText(lkelas.get(row).getKelasId().toString());  //awal dari form yang kedua dari model
        frame.getNamaKelas().setText(lkelas.get(row).getNamaKelas());
        frame.getTahunAjaran().setText(lkelas.get(row).getTahunAjaran().toString());
        frame.getCapacity().setText(lkelas.get(row).getCapacity().toString());
        frame.getNipWaliKelas().setSelectedItem(lkelas.get(row).getNipWaliKelas());
    }
    
    public void insert(String NipWaliKelas){
        if(
                !frame.getKodekelas().getText().trim().isEmpty() & 
                !frame.getNamaKelas().getText().trim().isEmpty() & 
                !frame.getTahunAjaran().getText().trim().isEmpty() & 
                !frame.getCapacity().getText().trim().isEmpty() &
                frame.getNipWaliKelas().getSelectedIndex() != 0
                
          )
        {
            Kelas b = new Kelas();
            b.setKelasId(frame.getKodekelas().getText());  //awal model kedua form
            b.setNamaKelas(frame.getNamaKelas().getText());
            b.setTahunAjaran(Integer.valueOf(frame.getTahunAjaran().getText()));
            b.setCapacity(Integer.valueOf(frame.getCapacity().getText()));
            b.setNipWaliKelas(NipWaliKelas);
            implKelas.insert(b);
        } else
        {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }
    
    public void reset()
    {
        frame.getKodekelas().setText("");
        frame.getNamaKelas().setText("");
        frame.getTahunAjaran().setText("");
        frame.getCapacity().setText("");
        frame.getNipWaliKelas().setSelectedIndex(0);
        
    }
    
    public void update(String NipWaliKelas){
        if(
                !frame.getKodekelas().getText().trim().isEmpty() & 
                !frame.getNamaKelas().getText().trim().isEmpty() & 
                !frame.getTahunAjaran().getText().trim().isEmpty() & 
                !frame.getCapacity().getText().trim().isEmpty() &
                frame.getNipWaliKelas().getSelectedIndex() != 0
                
          )
        {
            Kelas b = new Kelas();
            b.setKelasId(frame.getKodekelas().getText());  //awal model kedua form
            b.setNamaKelas(frame.getNamaKelas().getText());
            b.setTahunAjaran(Integer.valueOf(frame.getTahunAjaran().getText()));
            b.setCapacity(Integer.valueOf(frame.getCapacity().getText()));
            b.setNipWaliKelas(NipWaliKelas);
            b.setId(this.id);
            implKelas.update(b);
        } else
        {
            JOptionPane.showMessageDialog(frame, "Data Tidak Boleh Kosong");
        }
    }
    
    public void delete(){
        if (this.id != 0){
            int id = this.id;
            implKelas.delete(id);
            JOptionPane.showMessageDialog(null, "Hapus Data sukses");
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih Data yang akan di hapus");
        }
    }

    
}
