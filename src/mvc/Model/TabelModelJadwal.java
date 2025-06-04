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
public class TabelModelJadwal extends AbstractTableModel{
    List<Jadwal> lb;
    
    public TabelModelJadwal (List<Jadwal> lb){
        this.lb = lb;
    }
    
    @Override
    public int getRowCount() {
        return lb.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
    
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id";
            case 1:
                return "JadwalId";
            case 2:
                return "Hari";
            case 3:
                return "JamMulai";
            case 4:
                return "JamSelesai";
            case 5:
                return "KelasId";
            case 6:
                return "NIPGuru";
            case 7:
                return "KodeMapel";
//            case 8:
//                return "NamaGuru";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return lb.get(rowIndex).getId();
            case 1:
                return lb.get(rowIndex).getJadwalId();
            case 2:
                return lb.get(rowIndex).getHari();
            case 3:
                return lb.get(rowIndex).getJamMulai();
            case 4:
                return lb.get(rowIndex).getJamSelesai();
            case 5:
                return lb.get(rowIndex).getKelasId();
            case 6:
                return lb.get(rowIndex).getNipGuru();
            case 7:
                return lb.get(rowIndex).getKodeMapel();
//            case 8:
//                return lb.get(rowIndex).getNamaGuru();
            default:
                 return null; 
        }
    }
}
