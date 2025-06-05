/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class TabelModelSiswa extends AbstractTableModel {

    List<Siswa> lb;
    
    public TabelModelSiswa(List<Siswa> lb) {
        this.lb = lb;
    }
    
    @Override
    public int getRowCount() {
        return lb.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "NIS";
            case 1:
                return "Username";
            case 2:
                return "Password";
            case 3:
                return "Nama";
            case 4:
                return "Tanggal Lahir";
            case 5:
                return "Alamat";
            case 6:
                return "No Telp";
            case 7:
                return "Email";
            case 8:
                return "Kelas ID";
            case 9:
                return "Status";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lb.get(rowIndex).getNis();
            case 1:
                return lb.get(rowIndex).getUsername();
            case 2:
                return lb.get(rowIndex).getPassword();
            case 3:
                return lb.get(rowIndex).getNama();
            case 4:
                return lb.get(rowIndex).getTanggalLahir();
            case 5:
                return lb.get(rowIndex).getAlamat();
            case 6:
                return lb.get(rowIndex).getNoTelp();
            case 7:
                return lb.get(rowIndex).getEmail();
            case 8:
                return lb.get(rowIndex).getKelasId();
            case 9:
                return lb.get(rowIndex).getIsActive();
            default:
                return null;
        }
    }
}
