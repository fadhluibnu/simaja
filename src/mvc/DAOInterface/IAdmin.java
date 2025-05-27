/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAOInterface;
import java.util.List;
import mvc.Model.Admin;
/**
 *
 * @author gansp
 */
public interface IAdmin {
    public void insert(Admin b);
    public void update(Admin b);
    public void delete(int id);
    public List<Admin> getAll();
    public List<Admin> getCariNama(String nama);
}
