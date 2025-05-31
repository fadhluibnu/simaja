/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.DAOInterface;

import java.util.List;
import mvc.Model.Jadwal;

/**
 *
 * @author HP4
 */
public interface IJadwal {
    public void insert (Jadwal j);
    public void update (Jadwal j);
    public void delete (int id);
    public List<Jadwal> getAll();
    public List<Jadwal> getCariJadwal(String hari);
}
