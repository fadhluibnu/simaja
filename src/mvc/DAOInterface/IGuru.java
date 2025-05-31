/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAOInterface;

import java.util.List;
import mvc.Model.Guru;

/**
 *
 * @author NBN0C
 */
public interface IGuru {
    public void insert (Guru b);
    public void update (Guru b);
    public void delete (int nip);
    public List<Guru> getAll();
    public List<Guru> getCariNama(String nama);
    
}
