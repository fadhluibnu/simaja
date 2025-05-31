/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Model;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author gansp
 */
public class TabelModelAdmin extends AbstractTableModel {
    
    List<Admin> lb;
      public TabelModelAdmin(List<Admin> lb) {
        this.lb = lb;
      }

    @Override
    public int getRowCount() {
        return lb.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lb.get(rowIndex).getId();
            case 1:
                return lb.get(rowIndex).getAdminId();
            case 2:
                return lb.get(rowIndex).getUsername();
            case 3:
                return lb.get(rowIndex).getPassword();
            case 4:
                return lb.get(rowIndex).getNama();
            case 5:
                return lb.get(rowIndex).getEmail();
            case 6:
                return lb.get(rowIndex) .getNoTelp();
            case 7:
                return lb.get(rowIndex).getRole();
            case 8:
                return lb.get(rowIndex).getCreatedAt();
            case 9:
                return lb.get(rowIndex).getIsActive();
            default:
                return null;
        }
    }
    
     @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
            return "Id";
            case 1:
                return "Admin Id";
            case 2:
                return "Username";
            case 3:
                return "Password";
            case 4:
                return "Nama";
            case 5:
                return "Email";
            case 6:
                return "No Telp";
            case 7:
                return "Role";
            case 8:
                return "Created At";
            case 9:
                return "Is Active";
            default:
                return null;
        }
    }
    
    
}
