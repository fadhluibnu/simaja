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
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            case 0:
                return lb.get(rowIndex).getId();
            case 1:
                return lb.get(rowIndex).getKodeMapel();
            case 2:
                return lb.get(rowIndex).getNamaMapel();
            case 3:
                return lb.get(rowIndex).getKkm();
            case 4:
                return lb.get(rowIndex).getDeskripsi();
            case 5:
                return lb.get(rowIndex).getNipPengajar();
            case 6:
                return lb.get(rowIndex).getNamaGuru();
            default:
                return null;
        }
    }
    
    public String getColumnName(int column)
    {
        switch (column)
        {
            case 0:
                return "ID";
            case 1:
                return "Kode Mapel";
            case 2:
                return "Nama";
            case 3:
                return "KKM";
            case 4:
                return "Deskripsi";
            case 5:
                return "NIP Pengajar";
            case 6:
                return "Nama Pengajar";
            default:
                return null;
        }
    }
}
