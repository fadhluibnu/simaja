/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.Model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author NBN0C
 */
public class TabelModelGuru extends AbstractTableModel{
    List<Guru> lb;
    
    
    public TabelModelGuru(List<Guru> lb)
    {
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
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
               return lb.get(rowIndex).getId();
           case 1:
               return lb.get(rowIndex).getNip();
           case 2:
               return lb.get(rowIndex).getUsername();
           case 3:
               return lb.get(rowIndex).getPassword();
           case 4:
               return lb.get(rowIndex). getNama();
           case 5:
               return lb.get(rowIndex).getAlamat();
           case 6:
               return lb.get(rowIndex).getNoTelp();
           case 7:
               return lb.get(rowIndex).getEmail();
           case 8:
               return lb.get(rowIndex).getCreatedAt();
           case 9:
               return lb.get(rowIndex) .getIsActive();   
           default:
               return null;
        }
        
    }
    
    public String getColumnName(int column){
       switch (column){
           case 0:
                return "ID";
           case 1: 
               return"NIP";
           case 2 :
               return "Username";
           case 3 :
               return "Password";
           case 4 :
               return "Nama";
           case 5 :
               return "Alamat";
           case 6 : 
               return "NoTelp";
           case 7 :
               return "Email";
           case 8: 
               return " CreatedAt";
           case 9:
               return "IsActive";
           default : 
               return null;
       }
   }
    
    
}
