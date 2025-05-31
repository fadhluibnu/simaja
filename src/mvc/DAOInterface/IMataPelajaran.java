/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAOInterface;
import java.util.List;
import mvc.Model.MataPelajaran;
import mvc.Model.Guru;


/**
 *
 * @author fibnu
 */
public interface IMataPelajaran {
    
    public void insert(MataPelajaran b);
    public void update(MataPelajaran b);
    public void delete(int id);
    public List<MataPelajaran> getAll();
    public List<MataPelajaran> getCariNama(String nama);
    
    public List<Guru> getAllGuru();
}
