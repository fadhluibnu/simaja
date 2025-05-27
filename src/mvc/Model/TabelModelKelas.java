/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nabig
 */
public class TabelModelKelas extends AbstractTableModel{
     List<Kelas> lb;
     
    public TabelModelKelas(List<Kelas> lb) {
        this.lb = lb;
    }

    @Override
    public int getRowCount() {
        return lb.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column){
        case 0:
            return lb.get(row).getId();
        case 1:
            return lb.get(row).getKelasId();
        case 2:
            return lb.get(row).getNamaKelas();
        case 3:
            return lb.get(row).getNipWaliKelas();
        case 4:
            return lb.get(row).getTahunAjaran();
        case 5:
            return lb.get(row).getCapacity();
        default:
            return null;
        }
    }
    
    public String getColumnName(int column){
        switch (column){
            case 0:
                return "Id";
            case 1:
                return "KelasId";
            case 2:
                return "NamaKelas";
            case 3:
                return "NipWaliKelas";
            case 4:
                return "TahunAjaran";
            case 5:
                return "Capacity";
            default:
                return null;
        }
    }
    
}
