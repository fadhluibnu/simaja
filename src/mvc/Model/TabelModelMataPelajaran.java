/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Model;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author fibnu
 */
public class TabelModelMataPelajaran extends AbstractTableModel {
    
    List<MataPelajaran> lb;
    
    public TabelModelMataPelajaran(List<MataPelajaran> lb)
    {
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            case 0:
                return lb.get(rowIndex).getKodeMapel();
            case 1:
                return lb.get(rowIndex).getNamaMapel();
            case 2:
                return lb.get(rowIndex).getKkm();
            case 3:
                return lb.get(rowIndex).getDeskripsi();
            case 4:
                return lb.get(rowIndex).getNipPengajar();
            default:
                return null;
        }
    }
    
    public String getColumnName(int column)
    {
        switch (column)
        {
            case 0:
                return "Kode Mapel";
            case 1:
                return "Nama";
            case 2:
                return "KKM";
            case 3:
                return "Deskripsi";
            case 4:
                return "NIP Pengajar";
            default:
                return null;
        }
    }
}
